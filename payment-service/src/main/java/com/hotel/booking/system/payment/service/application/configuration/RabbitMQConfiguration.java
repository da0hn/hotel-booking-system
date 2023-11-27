package com.hotel.booking.system.payment.service.application.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotel.booking.system.payment.service.application.configuration.properties.ExchangeProperties;
import com.hotel.booking.system.payment.service.application.configuration.properties.QueueProperties;
import com.hotel.booking.system.payment.service.application.configuration.properties.RoutingKeyProperties;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class RabbitMQConfiguration {

  private final RoutingKeyProperties routingKeyProperties;

  private final QueueProperties queueProperties;

  private final ExchangeProperties exchangeProperties;

  @Bean
  public DirectExchange paymentExchange() {
    return new DirectExchange(this.exchangeProperties.payment());
  }

  @Bean
  public Queue paymentRequestQueue() {
    return new Queue(this.queueProperties.paymentRequest(), true);
  }

  @Bean
  public Queue paymentConfirmationQueue() {
    return new Queue(this.queueProperties.paymentConfirmation(), true);
  }

  @Bean
  public Binding paymentConfirmationBinding(
    final DirectExchange paymentExchange,
    final Queue paymentConfirmationQueue
  ) {
    return BindingBuilder.bind(paymentConfirmationQueue)
      .to(paymentExchange)
      .with(this.routingKeyProperties.paymentConfirmation());
  }

  @Bean
  public MessageConverter jsonMessageConverter(final ObjectMapper objectMapper) {
    return new Jackson2JsonMessageConverter(objectMapper);
  }

}
