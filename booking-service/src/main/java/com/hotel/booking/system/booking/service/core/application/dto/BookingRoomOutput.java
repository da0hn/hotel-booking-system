package com.hotel.booking.system.booking.service.core.application.dto;

import com.hotel.booking.system.booking.service.core.domain.entity.Booking;
import com.hotel.booking.system.commons.core.domain.valueobject.CustomerReservationStatus;
import com.hotel.booking.system.commons.core.domain.valueobject.FailureMessages;

public record BookingRoomOutput(
  Booking booking,
  CustomerReservationStatus status,
  FailureMessages failureMessages
) {
}
