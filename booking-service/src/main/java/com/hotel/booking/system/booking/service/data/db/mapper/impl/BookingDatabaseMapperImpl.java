package com.hotel.booking.system.booking.service.data.db.mapper.impl;

import com.hotel.booking.system.booking.service.core.domain.entity.Booking;
import com.hotel.booking.system.booking.service.core.domain.entity.BookingPeriod;
import com.hotel.booking.system.booking.service.core.domain.entity.BookingRoom;
import com.hotel.booking.system.booking.service.core.domain.valueobject.BookingId;
import com.hotel.booking.system.booking.service.core.domain.valueobject.BookingRoomId;
import com.hotel.booking.system.booking.service.data.db.entity.BookingEntity;
import com.hotel.booking.system.booking.service.data.db.entity.BookingRoomEntity;
import com.hotel.booking.system.booking.service.data.db.mapper.BookingDatabaseMapper;
import com.hotel.booking.system.commons.core.domain.valueobject.CustomerId;
import com.hotel.booking.system.commons.core.domain.valueobject.Money;
import com.hotel.booking.system.commons.core.domain.valueobject.ReservationOrderId;
import com.hotel.booking.system.commons.core.domain.valueobject.RoomId;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BookingDatabaseMapperImpl implements BookingDatabaseMapper {
  @Override
  public Booking bookingEntityToBooking(final BookingEntity entity) {
    return Booking.builder()
      .id(BookingId.of(entity.getId()))
      .customerId(CustomerId.of(entity.getCustomerId()))
      .totalPrice(Money.of(entity.getTotalPrice()))
      .reservationOrderId(ReservationOrderId.of(entity.getReservationOrderId()))
      .bookingPeriod(BookingPeriod.of(entity.getCheckIn(), entity.getCheckOut()))
      .status(entity.getStatus())
      .bookingRooms(
        entity.getBookingRooms().stream()
          .map(this::bookingRoomEntityToBookingRoom)
          .collect(Collectors.toList())
      )
      .build();
  }

  private BookingRoom bookingRoomEntityToBookingRoom(final BookingRoomEntity entity) {
    return BookingRoom.builder()
      .id(BookingRoomId.of(entity.getId()))
      .bookingId(BookingId.of(entity.getBooking().getId()))
      .roomId(RoomId.of(entity.getRoom().getId()))
      .price(Money.of(entity.getPrice()))
      .quantity(entity.getQuantity())
      .build();
  }
}
