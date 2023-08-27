package com.hotel.booking.system.booking.service.core.application.dto;

import com.hotel.booking.system.commons.core.domain.valueobject.BookingStatus;

public record UpdateBookingStatusOutput(
  String reservationOrderId,
  String customerId,
  BookingStatus status
) {
}
