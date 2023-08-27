package com.hotel.booking.system.customer.service.core.ports.spi.messaging.listener;

import com.hotel.booking.system.commons.core.domain.event.customer.CustomerBookingStatusUpdatedEvent;

@FunctionalInterface
public interface CustomerBookingStatusUpdatedListener {

  void listen(CustomerBookingStatusUpdatedEvent event);

}
