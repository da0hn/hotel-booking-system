package com.hotel.booking.system.customer.service.core.ports.spi.messaging.listener;

import com.hotel.booking.system.commons.core.domain.event.customer.CustomerBookingStatusUpdatedEvent;

import java.util.List;

@FunctionalInterface
public interface CustomerBookingStatusUpdatedListener {

  void listen(List<CustomerBookingStatusUpdatedEvent> event);

}
