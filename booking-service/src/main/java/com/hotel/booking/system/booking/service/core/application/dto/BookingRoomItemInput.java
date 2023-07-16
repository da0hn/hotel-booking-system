package com.hotel.booking.system.booking.service.core.application.dto;

import java.math.BigDecimal;

public record BookingRoomItemInput(
  String roomId,
  Integer quantity,
  BigDecimal price
) {
}
