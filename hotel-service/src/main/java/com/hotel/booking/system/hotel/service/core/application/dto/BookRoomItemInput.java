package com.hotel.booking.system.hotel.service.core.application.dto;

import lombok.Builder;

@Builder
public record BookRoomItemInput(
  String roomId,
  Integer roomQuantity
) {
}
