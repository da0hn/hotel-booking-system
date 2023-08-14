package com.hotel.booking.system.hotel.service.core.ports.spi.messaging.listener;

import com.hotel.booking.system.commons.core.domain.event.BookingRoomResponseEvent;

@FunctionalInterface
public interface BookingRoomResponseListener {

  void listen(BookingRoomResponseEvent event);

}
