package com.hotel.booking.system.hotel.service.core.ports.spi.messaging;

import com.hotel.booking.system.hotel.service.core.domain.event.BookingRoomRequestedEvent;

@FunctionalInterface
public interface BookingRoomRequestedPublisher {
  void publish(BookingRoomRequestedEvent event);
}
