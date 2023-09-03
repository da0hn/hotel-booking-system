package com.hotel.booking.system.customer.service.core.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotel.booking.system.commons.core.domain.valueobject.CustomerReservationStatus;

import java.time.LocalDateTime;

public record TimelineItem(
  CustomerReservationStatus status,
  @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  LocalDateTime occurredAt
) {
}
