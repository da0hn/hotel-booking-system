package com.hotel.booking.system.hotel.service.core.ports.spi.messaging.listener;

import com.hotel.booking.system.commons.core.domain.event.PaymentResponseEvent;

@FunctionalInterface
public interface PaymentResponseListener {

  void listen(PaymentResponseEvent event);

}
