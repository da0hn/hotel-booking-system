package com.hotel.booking.system.customer.service.core.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotel.booking.system.commons.core.domain.valueobject.CustomerReservationStatus;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
public record ReservationOrderDetailOutput(
  String customerId,
  String customerName,
  String customerCpf,
  String reservationOrderId,
  String hotelId,
  @JsonFormat(pattern = "dd/MM/yyyy")
  LocalDate checkIn,
  @JsonFormat(pattern = "dd/MM/yyyy")
  LocalDate checkOut,
  BigDecimal totalPrice,
  CustomerReservationStatus status,
  List<TimelineItem> timeline
) {

}
