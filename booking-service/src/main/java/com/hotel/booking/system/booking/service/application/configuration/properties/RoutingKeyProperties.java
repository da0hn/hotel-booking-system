package com.hotel.booking.system.booking.service.application.configuration.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "app.rabbitmq.routing-key")
public record RoutingKeyProperties(
  String bookingRoomRequested,
  String bookingRoomConfirmation,
  String bookingRoomStatusChanged
) {

}
