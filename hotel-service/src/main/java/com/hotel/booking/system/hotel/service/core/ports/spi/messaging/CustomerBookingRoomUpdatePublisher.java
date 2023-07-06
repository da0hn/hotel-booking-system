package com.hotel.booking.system.hotel.service.core.ports.spi.messaging;

import com.hotel.booking.system.hotel.service.core.domain.event.BookingRoomInitiatedEvent;

public interface CustomerBookingRoomUpdatePublisher {
  void publish(BookingRoomInitiatedEvent event);
}
