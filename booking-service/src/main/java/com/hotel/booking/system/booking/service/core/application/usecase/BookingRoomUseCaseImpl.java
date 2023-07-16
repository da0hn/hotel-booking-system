package com.hotel.booking.system.booking.service.core.application.usecase;

import com.hotel.booking.system.booking.service.core.application.dto.BookingRoomInput;
import com.hotel.booking.system.booking.service.core.application.dto.BookingRoomOutput;
import com.hotel.booking.system.booking.service.core.domain.entity.Booking;
import com.hotel.booking.system.booking.service.core.ports.api.mapper.BookingUseCaseMapper;
import com.hotel.booking.system.booking.service.core.ports.api.usecase.BookingRoomUseCase;
import com.hotel.booking.system.booking.service.core.ports.spi.repository.BookingRepository;
import com.hotel.booking.system.commons.core.domain.valueobject.FailureMessages;
import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;

@Slf4j
public class BookingRoomUseCaseImpl implements BookingRoomUseCase {

  private static final String BOOKING_CONFLICT_TEMPLATE_MESSAGE = "Booking conflict: The Room {0} is already booked for period {1}";
  private final BookingRepository bookingRepository;
  private final BookingUseCaseMapper mapper;

  public BookingRoomUseCaseImpl(
    final BookingRepository bookingRepository,
    final BookingUseCaseMapper mapper
  ) {
    this.bookingRepository = bookingRepository;
    this.mapper = mapper;
  }

  private void initializeAndValidate(final Booking booking) {
    log.info("Initializing and validating Booking reservationOrderId={}", booking.getReservationOrderId());
    booking.initialize();
    booking.validate();
  }

  @Override
  public BookingRoomOutput execute(final BookingRoomInput input) {
    final var booking = this.mapper.bookingRoomInputToBooking(input);
    final var failureMessages = FailureMessages.empty();
    this.initializeAndValidate(booking);
    this.verifyRoomAvailability(booking, failureMessages);
    if (failureMessages.isNotEmpty()) {
      return null;
    }
    return null;
  }

  private void verifyRoomAvailability(final Booking booking, final FailureMessages failureMessages) {
    log.info("Verifying rooms availability reservationOrderId={}", booking.getReservationOrderId());
    for (final var bookingRoom : booking.getBookingRooms()) {
      final var bookings = this.bookingRepository.findBookingByRoomIdAndPeriod(
        bookingRoom.getRoomId(),
        booking.getBookingPeriod()
      );
      final var bookingConflicts = bookings.stream()
        .filter(item -> booking.isBookingPeriodContainedIn(item.getBookingPeriod()))
        .toList();
      log.info(
        "Found {} booking conflicts for roomId={}, reservationOrderId={}",
        bookingConflicts.size(),
        bookingRoom.getRoomId(),
        booking.getReservationOrderId()
      );
      final var conflictsAsFailureMessages = bookingConflicts.stream()
        .map(Booking::getBookingPeriod)
        .map(item -> MessageFormat.format(
          BOOKING_CONFLICT_TEMPLATE_MESSAGE,
          bookingRoom.getRoomId(),
          item.periodAsString()
        ))
        .toList();
      failureMessages.addAll(conflictsAsFailureMessages);
    }
  }
}
