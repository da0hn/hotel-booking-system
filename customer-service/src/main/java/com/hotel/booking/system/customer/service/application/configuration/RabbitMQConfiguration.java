package com.hotel.booking.system.customer.service.application.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotel.booking.system.customer.service.application.configuration.properties.ExchangeProperties;
import com.hotel.booking.system.customer.service.application.configuration.properties.QueueProperties;
import com.hotel.booking.system.customer.service.application.configuration.properties.RoutingKeyProperties;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
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
  public TopicExchange customerBookingExchange() {
    return new TopicExchange(this.exchangeProperties.customerBooking());
  }


  @Bean
  public Queue customerBookingUpdateQueue() {
    return new Queue(this.queueProperties.customerBookingUpdate(), true);
  }

  @Bean
  public Binding customerBookingUpdateBinding(
    final TopicExchange customerBookingExchange,
    final Queue customerBookingUpdateQueue
  ) {
    return BindingBuilder.bind(customerBookingUpdateQueue)
      .to(customerBookingExchange)
      .with(this.routingKeyProperties.customerBookingUpdate());
  }


  @Bean
  public MessageConverter jsonMessageConverter(final ObjectMapper objectMapper) {
    return new Jackson2JsonMessageConverter(objectMapper);
  }
}
