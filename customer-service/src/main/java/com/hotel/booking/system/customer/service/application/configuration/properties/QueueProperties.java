package com.hotel.booking.system.customer.service.application.configuration.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "app.rabbitmq.queue")
public record QueueProperties(
  String customerBookingUpdate
) {

}
