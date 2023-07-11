package com.hotel.booking.system.booking.service.application.configuration.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "app.rabbitmq.queue")
public record QueueProperties(
  String bookingRoomRequested,
  String bookingRoomConfirmation
) {

}
