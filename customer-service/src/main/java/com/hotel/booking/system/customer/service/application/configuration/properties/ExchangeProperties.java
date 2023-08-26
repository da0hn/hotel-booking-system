package com.hotel.booking.system.customer.service.application.configuration.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "app.rabbitmq.exchange")
public record ExchangeProperties(
  String customerBooking
) {

}
