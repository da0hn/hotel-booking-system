package com.hotel.booking.system.booking.service.core.application.usecase;

import com.hotel.booking.system.booking.service.core.application.dto.BookingRoomInput;
import com.hotel.booking.system.booking.service.core.application.dto.BookingRoomOutput;
import com.hotel.booking.system.booking.service.core.application.service.BookingInitializer;
import com.hotel.booking.system.booking.service.core.application.service.VerifyRoomAvailability;
import com.hotel.booking.system.booking.service.core.domain.entity.Booking;
import com.hotel.booking.system.booking.service.core.ports.api.mapper.BookingUseCaseMapper;
import com.hotel.booking.system.booking.service.core.ports.api.usecase.BookingRoomUseCase;
import com.hotel.booking.system.booking.service.core.ports.spi.repository.BookingRepository;
import com.hotel.booking.system.commons.core.domain.valueobject.CustomerReservationStatus;
import com.hotel.booking.system.commons.core.domain.valueobject.FailureMessages;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BookingRoomUseCaseImpl implements BookingRoomUseCase {

  private final BookingRepository bookingRepository;
  private final BookingUseCaseMapper mapper;
  private final VerifyRoomAvailability verifyRoomAvailability;
  private final BookingInitializer bookingInitializer;

  public BookingRoomUseCaseImpl(
    final BookingRepository bookingRepository,
    final BookingUseCaseMapper mapper,
    final VerifyRoomAvailability verifyRoomAvailability,
    final BookingInitializer bookingInitializer
  ) {
    this.bookingRepository = bookingRepository;
    this.mapper = mapper;
    this.verifyRoomAvailability = verifyRoomAvailability;
    this.bookingInitializer = bookingInitializer;
  }

  private void initializeAndValidate(final Booking booking, final FailureMessages failureMessages) {
    final var collectedFailureMessages = this.bookingInitializer.execute(booking);
    failureMessages.addAll(collectedFailureMessages);
  }

  @Override
  public BookingRoomOutput execute(final BookingRoomInput input) {
    final var booking = this.mapper.bookingRoomInputToBooking(input);
    final var failureMessages = FailureMessages.empty();
    this.initializeAndValidate(booking, failureMessages);
    this.verifyRoomAvailability(booking, failureMessages);
    if (failureMessages.isNotEmpty()) {
      return new BookingRoomOutput(booking, CustomerReservationStatus.RESERVATION_FAILED, failureMessages);
    }
    this.bookingRepository.save(booking);
    return new BookingRoomOutput(booking, CustomerReservationStatus.AWAITING_PAYMENT, failureMessages);
  }

  private void verifyRoomAvailability(final Booking booking, final FailureMessages failureMessages) {
    final var collectedFailureMessages = this.verifyRoomAvailability.execute(booking);
    failureMessages.addAll(collectedFailureMessages);
  }
}
