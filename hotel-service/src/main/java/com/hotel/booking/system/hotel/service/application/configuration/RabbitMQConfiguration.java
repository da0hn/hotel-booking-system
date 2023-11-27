package com.hotel.booking.system.hotel.service.application.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotel.booking.system.hotel.service.application.configuration.properties.ExchangeProperties;
import com.hotel.booking.system.hotel.service.application.configuration.properties.QueueProperties;
import com.hotel.booking.system.hotel.service.application.configuration.properties.RoutingKeyProperties;
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
  public DirectExchange bookingRoomExchange() {
    return new DirectExchange(this.exchangeProperties.bookingRoom());
  }

  @Bean
  public DirectExchange paymentExchange() {
    return new DirectExchange(this.exchangeProperties.payment());
  }

  @Bean
  public DirectExchange customerBookingExchange() {
    return new DirectExchange(this.exchangeProperties.customerBooking());
  }

  @Bean
  public Queue bookingRoomRequestedQueue() {
    return new Queue(this.queueProperties.bookingRoomRequested(), true);
  }

  @Bean
  public Queue bookingRoomConfirmationQueue() {
    return new Queue(this.queueProperties.bookingRoomConfirmation(), true);
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
  public Queue customerBookingUpdateQueue() {
    return new Queue(this.queueProperties.customerBookingUpdate(), true);
  }

  @Bean
  public Queue bookingRoomStatusChangedQueue() {
    return new Queue(this.queueProperties.bookingRoomStatusChanged(), true);
  }

  @Bean
  public Binding bookingRoomRequestedBinding(
    final DirectExchange bookingRoomExchange,
    final Queue bookingRoomRequestedQueue
  ) {
    return BindingBuilder.bind(bookingRoomRequestedQueue)
      .to(bookingRoomExchange)
      .with(this.routingKeyProperties.bookingRoomRequested());
  }

  @Bean
  public Binding bookingRoomConfirmationBinding(
    final DirectExchange bookingRoomExchange,
    final Queue bookingRoomConfirmationQueue
  ) {
    return BindingBuilder.bind(bookingRoomConfirmationQueue)
      .to(bookingRoomExchange)
      .with(this.routingKeyProperties.bookingRoomConfirmation());
  }

  @Bean
  public Binding paymentRequestBinding(
    final DirectExchange paymentExchange,
    final Queue paymentRequestQueue
  ) {
    return BindingBuilder.bind(paymentRequestQueue)
      .to(paymentExchange)
      .with(this.routingKeyProperties.paymentRequest());
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
  public Binding customerBookingUpdateBinding(
    final DirectExchange customerBookingExchange,
    final Queue customerBookingUpdateQueue
  ) {
    return BindingBuilder.bind(customerBookingUpdateQueue)
      .to(customerBookingExchange)
      .with(this.routingKeyProperties.customerBookingUpdate());
  }

  @Bean
  public Binding bookingRoomStatusChangedBinding(
    final DirectExchange bookingRoomExchange,
    final Queue bookingRoomStatusChangedQueue
  ) {
    return BindingBuilder.bind(bookingRoomStatusChangedQueue)
      .to(bookingRoomExchange)
      .with(this.routingKeyProperties.bookingRoomStatusChanged());
  }

  @Bean
  public MessageConverter jsonMessageConverter(final ObjectMapper objectMapper) {
    return new Jackson2JsonMessageConverter(objectMapper);
  }

}
