package com.hotel.booking.system.commons.core.domain.event;

public abstract sealed class BookingRoomResponseEvent implements Event
  permits BookingRoomPendingEvent, BookingRoomFailedEvent {
}
