package com.hotel.booking.system.customer.service.core.application.dto;

import com.hotel.booking.system.commons.core.domain.valueobject.CustomerReservationStatus;

public record UpdateCustomerBookingStatusInput(
  String customerId,
  String reservationOrderId,
  CustomerReservationStatus status
) {
}
