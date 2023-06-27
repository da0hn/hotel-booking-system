package com.hotel.booking.system.hotel.service.core.application.dto;

import java.util.List;

public record RegisterHotelOutput(
  String hotelId,
  List<String> roomsId
) {}
