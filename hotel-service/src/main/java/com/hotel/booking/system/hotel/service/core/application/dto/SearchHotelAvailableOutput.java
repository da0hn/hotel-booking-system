package com.hotel.booking.system.hotel.service.core.application.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record SearchHotelAvailableOutput(
  String id,
  String name,
  String description,
  String category,
  String address,
  String state,
  String city,
  String country,
  List<SearchHotelAvailableRoomOutput> rooms
) {
}
