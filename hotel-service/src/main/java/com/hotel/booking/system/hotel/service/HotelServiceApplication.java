package com.hotel.booking.system.hotel.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class HotelServiceApplication {

  public static void main(final String[] args) {
    SpringApplication.run(HotelServiceApplication.class, args);
  }

}
