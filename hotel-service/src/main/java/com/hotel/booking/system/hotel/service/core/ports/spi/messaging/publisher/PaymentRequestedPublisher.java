package com.hotel.booking.system.hotel.service.core.ports.spi.messaging.publisher;

import com.hotel.booking.system.commons.core.domain.event.PaymentRequestedEvent;

@FunctionalInterface
public interface PaymentRequestedPublisher {

  void publish(PaymentRequestedEvent event);

}
