package com.hotel.booking.system.payment.service.core.ports.spi.messaging.listener;

import com.hotel.booking.system.commons.core.domain.event.PaymentRequestedEvent;

@FunctionalInterface
public interface PaymentRequestedListener {

  void listen(PaymentRequestedEvent event);

}
