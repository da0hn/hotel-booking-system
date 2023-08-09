package com.hotel.booking.system.booking.service.core.application.mapper;

import com.hotel.booking.system.booking.service.core.application.dto.BookingRoomInput;
import com.hotel.booking.system.booking.service.core.application.dto.BookingRoomItemInput;
import com.hotel.booking.system.booking.service.core.application.dto.BookingRoomOutput;
import com.hotel.booking.system.booking.service.core.domain.entity.Booking;
import com.hotel.booking.system.booking.service.core.domain.entity.BookingPeriod;
import com.hotel.booking.system.booking.service.core.domain.entity.BookingRoom;
import com.hotel.booking.system.booking.service.core.ports.api.mapper.BookingUseCaseMapper;
import com.hotel.booking.system.commons.core.domain.event.BookingRoomFailedEvent;
import com.hotel.booking.system.commons.core.domain.event.BookingRoomPendingEvent;
import com.hotel.booking.system.commons.core.domain.event.BookingRoomRepresentation;
import com.hotel.booking.system.commons.core.domain.event.BookingRoomRequestedEvent;
import com.hotel.booking.system.commons.core.domain.valueobject.CustomerId;
import com.hotel.booking.system.commons.core.domain.valueobject.Money;
import com.hotel.booking.system.commons.core.domain.valueobject.ReservationOrderId;
import com.hotel.booking.system.commons.core.domain.valueobject.RoomId;

import java.util.stream.Collectors;

public class BookingUseCaseMapperImpl implements BookingUseCaseMapper {

  private BookingRoom bookingRoomItemInputToBookingRoom(
    final BookingRoomItemInput item
  ) {
    return BookingRoom.builder()
      .roomId(RoomId.of(item.roomId()))
      .quantity(item.quantity())
      .price(Money.of(item.price()))
      .build();
  }

  @Override
  public BookingRoomInput bookingRoomRequestedEventToBookingRoomInput(
    final BookingRoomRequestedEvent event
  ) {
    return BookingRoomInput.builder()
      .reservationOrderId(event.getReservationOrderId())
      .customerId(event.getCustomerId())
      .price(event.getPrice())
      .guests(event.getGuests())
      .checkIn(event.getCheckIn())
      .checkOut(event.getCheckOut())
      .rooms(
        event.getRooms().stream()
          .map(item -> new BookingRoomItemInput(
              item.getRoomId(),
              item.getRoomQuantity(),
              item.getRoomPrice()
            )
          )
          .collect(Collectors.toList())
      )
      .build();
  }

  @Override
  public Booking bookingRoomInputToBooking(final BookingRoomInput input) {
    return Booking.builder()
      .reservationOrderId(ReservationOrderId.of(input.reservationOrderId()))
      .customerId(CustomerId.of(input.customerId()))
      .bookingPeriod(BookingPeriod.of(input.checkIn(), input.checkOut()))
      .totalPrice(Money.of(input.price()))
      .bookingRooms(
        input.rooms().stream()
          .map(this::bookingRoomItemInputToBookingRoom)
          .collect(Collectors.toList())
      )
      .build();
  }

  @Override
  public BookingRoomFailedEvent bookingRoomOutputToBookingRoomFailedEvent(final BookingRoomOutput output) {
    return BookingRoomFailedEvent.builder()
      .reservationOrderId(output.booking().getReservationOrderId().toString())
      .customerId(output.booking().getCustomerId().toString())
      .checkIn(output.booking().getBookingPeriod().getCheckIn())
      .checkOut(output.booking().getBookingPeriod().getCheckOut())
      .status(output.status())
      .failureMessages(output.failureMessages().data())
      .build();
  }

  @Override
  public BookingRoomPendingEvent bookingRoomOutputToBookingRoomResponseEvent(final BookingRoomOutput output) {
    return BookingRoomPendingEvent.builder()
      .bookingRoomId(output.booking().getId().toString())
      .reservationOrderId(output.booking().getReservationOrderId().toString())
      .customerId(output.booking().getCustomerId().toString())
      .totalPrice(output.booking().getTotalPrice().getValue())
      .guests(null) // TODO: verificar guests
      .checkIn(output.booking().getBookingPeriod().getCheckIn())
      .checkOut(output.booking().getBookingPeriod().getCheckOut())
      .status(output.status())
      .rooms(
        output.booking().getBookingRooms().stream()
          .map(this::bookingRoomItemInputToBookingRoom)
          .collect(Collectors.toList())
      )
      .build();
  }

  private BookingRoomRepresentation bookingRoomItemInputToBookingRoom(final BookingRoom bookingRoom) {
    return BookingRoomRepresentation.builder()
      .roomId(bookingRoom.getRoomId().toString())
      .quantity(bookingRoom.getQuantity())
      .price(bookingRoom.getPrice().getValue())
      .build();
  }
}
