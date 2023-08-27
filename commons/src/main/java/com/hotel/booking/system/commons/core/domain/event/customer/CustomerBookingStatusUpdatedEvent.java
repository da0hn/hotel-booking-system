package com.hotel.booking.system.commons.core.domain.event.customer;

import com.hotel.booking.system.commons.core.domain.event.Event;

public abstract sealed class CustomerBookingStatusUpdatedEvent
  implements Event permits
  CustomerBookingInitiatedEvent,
  CustomerBookingPaymentRequestedEvent,
  CustomerBookingPaymentCompletedEvent,
  CustomerBookingCompletedEvent,
  CustomerBookingPaymentFailedEvent,
  CustomerBookingRejectedEvent {
}
