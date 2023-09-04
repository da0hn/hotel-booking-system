package com.hotel.booking.system.commons.core.domain.event.customer;

import com.hotel.booking.system.commons.core.domain.event.Event;
import com.hotel.booking.system.commons.core.domain.valueobject.CustomerReservationStatus;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Getter
@SuperBuilder
public abstract sealed class CustomerBookingStatusUpdatedEvent
  implements Event permits CustomerBookingFailureStatusUpdateEvent, CustomerBookingCompletedEvent, CustomerBookingInitiatedEvent, CustomerBookingPaymentCompletedEvent, CustomerBookingPaymentRequestedEvent {

  private final Instant createdAt = Instant.now();
  private final String reservationOrderId;
  private final String customerId;
  private final CustomerReservationStatus status;

  protected CustomerBookingStatusUpdatedEvent(
    final String reservationOrderId,
    final String customerId,
    final CustomerReservationStatus status
  ) {
    this.reservationOrderId = reservationOrderId;
    this.customerId = customerId;
    this.status = status;
  }
}
