package com.hotel.booking.system.hotel.service.core.application.messaging;

import com.hotel.booking.system.commons.core.domain.event.PaymentResponseEvent;
import com.hotel.booking.system.hotel.service.core.ports.api.messaging.PaymentResponseHandler;

public class PaymentResponseHandlerImpl implements PaymentResponseHandler {
  @Override
  public void handle(final PaymentResponseEvent event) {

  }
}
