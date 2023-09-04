package com.hotel.booking.system.commons.core.domain.event.customer;


import com.hotel.booking.system.commons.core.domain.valueobject.CustomerReservationStatus;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
public abstract sealed class CustomerBookingFailureStatusUpdateEvent extends CustomerBookingStatusUpdatedEvent permits CustomerBookingPaymentFailedEvent, CustomerBookingRejectedEvent {


  private final List<String> failureMessages;

  protected CustomerBookingFailureStatusUpdateEvent(
    final String reservationOrderId,
    final String customerId,
    final CustomerReservationStatus status,
    final List<String> failureMessages
  ) {
    super(reservationOrderId, customerId, status);
    this.failureMessages = failureMessages;
  }
}
