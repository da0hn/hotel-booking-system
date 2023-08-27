package com.hotel.booking.system.customer.service.core.application.dto;

import com.hotel.booking.system.commons.core.domain.valueobject.CustomerReservationStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public record InitializeReservationOrderInput(
  String reservationOrderId,
  String customerId,
  String hotelId,
  BigDecimal totalPrice,
  Integer guests,
  LocalDate checkIn,
  LocalDate checkOut,
  CustomerReservationStatus status
) {
}
