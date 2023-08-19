package com.hotel.booking.system.payment.service.application.configuration.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "app.rabbitmq.queue")
public record QueueProperties(
  String paymentRequest,
  String paymentConfirmation
) {

}
