package com.hotel.booking.system.customer.service.core.application.dto;

import com.hotel.booking.system.commons.core.domain.valueobject.CustomerReservationStatus;
import com.hotel.booking.system.commons.core.domain.valueobject.FailureMessages;

public record UpdateCustomerBookingFailureStatusInput(
  String customerId,
  String reservationOrderId,
  CustomerReservationStatus status,
  FailureMessages failureMessages
) {
}
