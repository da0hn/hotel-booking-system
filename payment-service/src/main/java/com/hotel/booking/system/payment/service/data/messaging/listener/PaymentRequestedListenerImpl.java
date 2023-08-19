package com.hotel.booking.system.payment.service.data.messaging.listener;

import com.hotel.booking.system.commons.core.domain.event.PaymentRequestedEvent;
import com.hotel.booking.system.payment.service.core.ports.api.messaging.PaymentRequestedHandler;
import com.hotel.booking.system.payment.service.core.ports.spi.messaging.listener.PaymentRequestedListener;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class PaymentRequestedListenerImpl implements PaymentRequestedListener {

  private final PaymentRequestedHandler handler;

  @Override
  @RabbitListener(queues = "${app.rabbitmq.queue.payment-request}")
  public void listen(final PaymentRequestedEvent event) {
    log.info("PaymentRequestedEvent received, {}", event);
    this.handler.handle(event);
  }
}
