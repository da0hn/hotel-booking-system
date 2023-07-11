package com.hotel.booking.system.booking.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class BookingServiceApplication {

  public static void main(final String[] args) {
    SpringApplication.run(BookingServiceApplication.class, args);
  }

}
