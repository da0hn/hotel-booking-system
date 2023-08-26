package com.hotel.booking.system.hotel.service.core.ports.spi.messaging.publisher;

import com.hotel.booking.system.commons.core.domain.event.customer.CustomerBookingStatusUpdatedEvent;

@FunctionalInterface
public interface CustomerBookingRoomStatusUpdatedPublisher {

  void publish(CustomerBookingStatusUpdatedEvent event);

}
