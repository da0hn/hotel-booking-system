package com.hotel.booking.system.booking.service.data.db.mapper.impl;

import com.hotel.booking.system.booking.service.core.domain.entity.Booking;
import com.hotel.booking.system.booking.service.core.domain.entity.BookingPeriod;
import com.hotel.booking.system.booking.service.core.domain.entity.BookingRoom;
import com.hotel.booking.system.booking.service.core.domain.valueobject.BookingId;
import com.hotel.booking.system.booking.service.core.domain.valueobject.BookingRoomId;
import com.hotel.booking.system.booking.service.data.db.entity.BookingEntity;
import com.hotel.booking.system.booking.service.data.db.entity.BookingRoomEntity;
import com.hotel.booking.system.booking.service.data.db.mapper.BookingDatabaseMapper;
import com.hotel.booking.system.booking.service.data.db.repository.adapters.RoomRepositoryAdapter;
import com.hotel.booking.system.commons.core.domain.valueobject.CustomerId;
import com.hotel.booking.system.commons.core.domain.valueobject.Money;
import com.hotel.booking.system.commons.core.domain.valueobject.ReservationOrderId;
import com.hotel.booking.system.commons.core.domain.valueobject.RoomId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class BookingDatabaseMapperImpl implements BookingDatabaseMapper {

  private final RoomRepositoryAdapter roomRepositoryAdapter;

  @Override
  public Booking bookingEntityToBooking(final BookingEntity entity) {
    return Booking.builder()
      .id(BookingId.of(entity.getId()))
      .customerId(CustomerId.of(entity.getCustomerId()))
      .totalPrice(Money.of(entity.getTotalPrice()))
      .reservationOrderId(ReservationOrderId.of(entity.getReservationOrderId()))
      .bookingPeriod(BookingPeriod.of(entity.getCheckIn(), entity.getCheckOut()))
      .status(entity.getStatus())
      .createdAt(entity.getCreatedAt())
      .updatedAt(entity.getUpdatedAt())
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

  private BookingRoomEntity bookingRoomToBookingRoomEntity(final BookingRoom entity) {
    return BookingRoomEntity.builder()
      .id(entity.getId().getValue())
      .price(entity.getPrice().getValue())
      .quantity(entity.getQuantity())
      .room(this.roomRepositoryAdapter.findRoomEntityById(entity.getRoomId()))
      .build();
  }


  @Override
  public BookingEntity bookingToBookingEntity(final Booking booking) {
    final var bookingEntity = BookingEntity.builder()
      .id(booking.getId().getValue())
      .customerId(booking.getCustomerId().getValue())
      .reservationOrderId(booking.getReservationOrderId().getValue())
      .status(booking.getStatus())
      .totalPrice(booking.getTotalPrice().getValue())
      .createdAt(booking.getCreatedAt())
      .updatedAt(booking.getUpdatedAt())
      .checkIn(booking.getBookingPeriod().getCheckIn())
      .checkOut(booking.getBookingPeriod().getCheckOut())
      .build();
    final var bookingRoomEntities = booking.getBookingRooms().stream()
      .map(this::bookingRoomToBookingRoomEntity)
      .collect(Collectors.toSet());
    bookingRoomEntities.forEach(bookingRoom -> bookingRoom.setBooking(bookingEntity));
    bookingEntity.setBookingRooms(bookingRoomEntities);

    return bookingEntity;
  }

}
