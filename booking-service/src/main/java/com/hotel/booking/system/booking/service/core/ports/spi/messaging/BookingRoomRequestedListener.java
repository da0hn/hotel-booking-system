package com.hotel.booking.system.booking.service.core.ports.spi.messaging;

import com.hotel.booking.system.commons.core.domain.event.BookingRoomRequestedEvent;

@FunctionalInterface
public interface BookingRoomRequestedListener {

  void listen(BookingRoomRequestedEvent event);

}
