package com.hotel.booking.system.payment.service.data.messaging.publisher;

import com.hotel.booking.system.commons.core.domain.event.PaymentResponseEvent;
import com.hotel.booking.system.payment.service.application.configuration.properties.ExchangeProperties;
import com.hotel.booking.system.payment.service.application.configuration.properties.RoutingKeyProperties;
import com.hotel.booking.system.payment.service.core.ports.spi.messaging.publisher.PaymentResponsePublisher;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class PaymentResponsePublisherImpl implements PaymentResponsePublisher {

  private final RabbitTemplate rabbitTemplate;
  private final RoutingKeyProperties routingKeyProperties;
  private final ExchangeProperties exchangeProperties;

  @Override
  public void publish(final PaymentResponseEvent event) {
    try {
      log.info(
        "Publishing event: {} to exchange: {} | routingKey={}",
        event,
        this.exchangeProperties.payment(),
        this.routingKeyProperties.paymentConfirmation()
      );
      this.rabbitTemplate.convertAndSend(
        this.exchangeProperties.payment(),
        this.routingKeyProperties.paymentConfirmation(),
        event
      );
      log.info(
        "Event: {} published successfully to exchange: {} | routingKey={}",
        event,
        this.exchangeProperties.payment(),
        this.routingKeyProperties.paymentConfirmation()
      );
    } catch (final Exception exception) {
      log.error(
        "Failed to publish event: {} to exchange={} | routingKey={}",
        event,
        this.exchangeProperties.payment(),
        this.routingKeyProperties.paymentConfirmation(),
        exception
      );
    }
  }

}
