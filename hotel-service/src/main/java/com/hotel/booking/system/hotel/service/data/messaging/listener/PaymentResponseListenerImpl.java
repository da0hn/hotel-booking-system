package com.hotel.booking.system.hotel.service.data.messaging.listener;

import com.hotel.booking.system.commons.core.domain.event.PaymentResponseEvent;
import com.hotel.booking.system.hotel.service.core.ports.api.messaging.PaymentResponseHandler;
import com.hotel.booking.system.hotel.service.core.ports.spi.messaging.listener.PaymentResponseListener;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class PaymentResponseListenerImpl implements PaymentResponseListener {

  private final PaymentResponseHandler handler;

  @Override
  @RabbitListener(queues = "${app.rabbitmq.queue.payment-confirmation}")
  public void listen(final PaymentResponseEvent event) {
    log.info("PaymentResponseEvent received: {}, ", event);
    this.handler.handle(event);
  }

}
