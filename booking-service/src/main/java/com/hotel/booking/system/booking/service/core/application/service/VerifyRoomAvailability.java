package com.hotel.booking.system.booking.service.core.application.service;

import com.hotel.booking.system.booking.service.core.domain.entity.Booking;
import com.hotel.booking.system.booking.service.core.domain.entity.BookingRoom;
import com.hotel.booking.system.booking.service.core.ports.spi.repository.BookingRepository;
import com.hotel.booking.system.booking.service.core.ports.spi.repository.RoomRepository;
import com.hotel.booking.system.commons.core.domain.valueobject.BookingStatus;
import com.hotel.booking.system.commons.core.domain.valueobject.FailureMessages;
import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Slf4j
public class VerifyRoomAvailability {


  private static final String ROOM_UNAVAILABLE_TEMPLATE_MESSAGE = "The room roomId={0} is no longer available for period {1}";
  private final BookingRepository bookingRepository;
  private final RoomRepository roomRepository;

  public VerifyRoomAvailability(final BookingRepository bookingRepository, final RoomRepository roomRepository) {
    this.bookingRepository = bookingRepository;
    this.roomRepository = roomRepository;
  }

  public FailureMessages execute(final Booking booking) {
    final var failureMessages = FailureMessages.empty();

    final var rooms = this.roomRepository.findAllByRoomId(booking.getRoomsId());

    log.info("Verifying rooms availability reservationOrderId={}", booking.getReservationOrderId());
    for (final var bookingRoom : booking.getBookingRooms()) {
      final var bookings = this.bookingRepository.findBookingByRoomIdAndPeriod(
        bookingRoom.getRoomId(),
        booking.getBookingPeriod()
      );
      final var bookingsOnPeriod = bookings.stream()
        .filter(item -> item.getStatus() != BookingStatus.FAILED)
        .filter(item -> booking.isBookingPeriodContainedIn(item.getBookingPeriod()))
        .toList();

      final var allBookingRoomsGroupedByRoomId = bookingsOnPeriod.stream()
        .flatMap(item -> item.getBookingRooms().stream())
        .collect(Collectors.groupingBy(BookingRoom::getRoomId));

      for (final var room : rooms) {
        final var bookingsForRoom = allBookingRoomsGroupedByRoomId.getOrDefault(room.getId(), new ArrayList<>());
        if (room.getQuantity() == bookingsForRoom.size()) {
          log.error(
            "The roomId={} is unavailable for period={}, reservationOrderId={}",
            bookingRoom.getRoomId(),
            booking.getBookingPeriod().periodAsString(),
            booking.getReservationOrderId()
          );
          failureMessages.add(MessageFormat.format(
            ROOM_UNAVAILABLE_TEMPLATE_MESSAGE,
            room.getId(),
            booking.getBookingPeriod().periodAsString()
          ));
        }
      }
    }

    return failureMessages;
  }

}
