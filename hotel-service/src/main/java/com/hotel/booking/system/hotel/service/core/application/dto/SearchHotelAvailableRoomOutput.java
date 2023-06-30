package com.hotel.booking.system.hotel.service.core.application.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record SearchHotelAvailableRoomOutput(
  String id,
  String name,
  String description,
  Integer capacity,
  BigDecimal currentPrice,
  Integer quantity
) {
}
