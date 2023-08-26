package com.hotel.booking.system.booking.service.core.ports.api.messaging;

import com.hotel.booking.system.commons.core.domain.event.BookingRoomStatusUpdatedEvent;

@FunctionalInterface
public interface BookingRoomStatusChangedHandler {

  void handle(BookingRoomStatusUpdatedEvent event);

}
