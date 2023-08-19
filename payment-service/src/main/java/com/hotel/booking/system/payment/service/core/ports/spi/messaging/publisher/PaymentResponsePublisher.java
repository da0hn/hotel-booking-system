package com.hotel.booking.system.payment.service.core.ports.spi.messaging.publisher;

import com.hotel.booking.system.commons.core.domain.event.PaymentResponseEvent;

@FunctionalInterface
public interface PaymentResponsePublisher {

  void publish(PaymentResponseEvent event);

}
