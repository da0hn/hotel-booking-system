package com.hotel.booking.system.customer.service.core.application.dto;

public record ReservationOrderDetailInput(
  String customerId,
  String reservationOrderId
) {
}
