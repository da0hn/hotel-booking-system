package com.hotel.booking.system.commons.core.domain.event.customer;

import com.hotel.booking.system.commons.core.domain.event.Event;

public abstract sealed class CustomerBookingStatusUpdatedEvent
  implements Event permits
  CustomerBookingInitiatedEvent,
  CustomerBookingPaymentCompletedEvent,
  CustomerBookingPaymentFailedEvent,
  CustomerBookingPaymentRequestedEvent,
  CustomerBookingRejectedEvent {
}
