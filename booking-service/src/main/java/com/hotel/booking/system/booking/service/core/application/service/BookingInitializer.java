package com.hotel.booking.system.booking.service.core.application.service;

import com.hotel.booking.system.booking.service.core.domain.entity.Booking;
import com.hotel.booking.system.commons.core.domain.valueobject.FailureMessages;
import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;

@Slf4j
public class BookingInitializer {

  private static final String BOOKING_INVALID_DATA_TEMPLATE_MESSAGE = "The booking with reservationOrderId={0} has inconsistent data";

  public FailureMessages execute(final Booking booking) {
    try {
      log.info("Initializing and validating Booking reservationOrderId={}", booking.getReservationOrderId());
      booking.initialize();
      booking.validate();
    } catch (final Exception e) {
      log.error(
        "The booking has inconsistent data '{}' for reservationOrderId={}",
        e.getMessage(),
        booking.getReservationOrderId()
      );
      return FailureMessages.of(
        MessageFormat.format(BOOKING_INVALID_DATA_TEMPLATE_MESSAGE, booking.getReservationOrderId())
      );
    }
    return FailureMessages.empty();
  }

}
