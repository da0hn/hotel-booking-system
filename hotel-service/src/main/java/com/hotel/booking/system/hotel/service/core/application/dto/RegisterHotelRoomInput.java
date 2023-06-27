package com.hotel.booking.system.hotel.service.core.application.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record RegisterHotelRoomInput(
  String name,
  String description,
  Integer capacity,
  BigDecimal currentPrice
) {
}
