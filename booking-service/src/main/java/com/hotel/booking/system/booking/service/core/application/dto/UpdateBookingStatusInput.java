package com.hotel.booking.system.booking.service.core.application.dto;

import com.hotel.booking.system.commons.core.domain.valueobject.BookingStatus;
import com.hotel.booking.system.commons.core.domain.valueobject.CustomerId;
import com.hotel.booking.system.commons.core.domain.valueobject.ReservationOrderId;
import lombok.Builder;

@Builder
public record UpdateBookingStatusInput(
  ReservationOrderId reservationOrderId,
  CustomerId customerId,
  BookingStatus status
) {
}
