package com.hotel.booking.system.hotel.service.core.ports.spi.messaging.publisher;

import com.hotel.booking.system.commons.core.domain.event.BookingRoomStatusUpdatedEvent;

@FunctionalInterface
public interface CustomerBookingRoomStatusUpdatedPublisher {

  void publish(BookingRoomStatusUpdatedEvent event);

}
