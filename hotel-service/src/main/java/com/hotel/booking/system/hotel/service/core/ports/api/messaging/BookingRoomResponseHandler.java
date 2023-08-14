package com.hotel.booking.system.hotel.service.core.ports.api.messaging;

import com.hotel.booking.system.commons.core.domain.event.BookingRoomResponseEvent;

@FunctionalInterface
public interface BookingRoomResponseHandler {

  void handle(BookingRoomResponseEvent event);

}
