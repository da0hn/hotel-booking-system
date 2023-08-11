package com.hotel.booking.system.booking.service.core.ports.spi.messaging.publisher;

import com.hotel.booking.system.commons.core.domain.event.BookingRoomResponseEvent;

public interface BookingRoomResponsePublisher {

  void publish(BookingRoomResponseEvent event);

}
