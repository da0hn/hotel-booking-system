package com.hotel.booking.system.commons.core.domain.event;

public abstract sealed class BookingRoomStatusUpdatedEvent
  implements Event permits
  BookingRoomInitiatedEvent,
  BookingRoomPaymentCompletedEvent,
  BookingRoomPaymentFailedEvent,
  BookingRoomPaymentRequestedEvent,
  BookingRoomRejectedEvent {
}
