package com.hotel.booking.system.hotel.service.core.application.dto;

import lombok.Builder;

@Builder
public record SearchHotelAvailableInput(
  String city,
  String state,
  String category,
  String name
) {
}
