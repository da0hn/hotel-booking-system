package com.hotel.booking.system.hotel.service.core.ports.spi.messaging.publisher;

import com.hotel.booking.system.commons.core.domain.event.BookingRoomInitiatedEvent;

public interface CustomerBookingRoomUpdatePublisher {
  void publish(BookingRoomInitiatedEvent event);
}