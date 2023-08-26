package com.hotel.booking.system.booking.service.core.ports.spi.messaging.listener;

import com.hotel.booking.system.commons.core.domain.event.BookingRoomStatusUpdatedEvent;

@FunctionalInterface
public interface BookingRoomStatusChangedListener {

  void listen(BookingRoomStatusUpdatedEvent event);

}
