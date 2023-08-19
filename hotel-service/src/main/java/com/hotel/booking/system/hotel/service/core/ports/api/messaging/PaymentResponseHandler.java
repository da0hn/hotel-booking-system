package com.hotel.booking.system.hotel.service.core.ports.api.messaging;

import com.hotel.booking.system.commons.core.domain.event.PaymentResponseEvent;

@FunctionalInterface
public interface PaymentResponseHandler {

  void handle(PaymentResponseEvent event);

}
