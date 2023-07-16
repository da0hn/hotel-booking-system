package com.hotel.booking.system.booking.service.core.ports.api.messaging;

import com.hotel.booking.system.commons.core.domain.event.BookingRoomRequestedEvent;

@FunctionalInterface
public interface BookingRoomRequestedHandler {

  void handle(BookingRoomRequestedEvent event);

}
