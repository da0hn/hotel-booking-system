package com.hotel.booking.system.customer.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class CustomerServiceApplication {

  public static void main(final String[] args) {
    SpringApplication.run(CustomerServiceApplication.class, args);
  }

}
