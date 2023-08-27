package com.hotel.booking.system.customer.service.core.ports.api.messaging;

import com.hotel.booking.system.commons.core.domain.event.customer.CustomerBookingStatusUpdatedEvent;

@FunctionalInterface
public interface CustomerBookingStatusUpdatedHandler {

  void handle(CustomerBookingStatusUpdatedEvent event);

}
