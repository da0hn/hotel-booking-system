package com.hotel.booking.system.hotel.service.core.application.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record RegisterHotelInput(
  String name,
  String description,
  String categoryId,
  String street,
  String cep,
  String localityId,
  List<RegisterHotelRoomInput> rooms
) {
}
