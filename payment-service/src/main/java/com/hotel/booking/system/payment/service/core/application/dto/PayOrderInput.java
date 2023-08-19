package com.hotel.booking.system.payment.service.core.application.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PayOrderInput(
  String bookingRoomId,
  String reservationOrderId,
  String customerId,
  BigDecimal totalPrice
) {
}
