package com.hotel.booking.system.booking.service.core.application.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
public record BookingRoomInput(
  String reservationOrderId,
  String customerId,
  BigDecimal price,
  Integer guests,
  LocalDate checkIn,
  LocalDate checkOut,
  List<BookingRoomItemInput> rooms
) {
}
