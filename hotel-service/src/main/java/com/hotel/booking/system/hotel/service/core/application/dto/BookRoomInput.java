package com.hotel.booking.system.hotel.service.core.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Set;

@Builder
public record BookRoomInput(
  String customerId,
  String hotelId,
  @Min(1)
  Integer guests,
  @JsonFormat(pattern = "dd/MM/yyyy")
  LocalDate checkIn,
  @JsonFormat(pattern = "dd/MM/yyyy")
  LocalDate checkOut,
  @NotEmpty
  Set<BookRoomItemInput> rooms
) {
}
