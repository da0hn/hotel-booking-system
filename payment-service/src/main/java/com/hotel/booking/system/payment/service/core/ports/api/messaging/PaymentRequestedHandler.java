package com.hotel.booking.system.payment.service.core.ports.api.messaging;

import com.hotel.booking.system.commons.core.domain.event.PaymentRequestedEvent;

@FunctionalInterface
public interface PaymentRequestedHandler {

  void handle(PaymentRequestedEvent event);

}
