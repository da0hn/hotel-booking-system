package com.hotel.booking.system.payment.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class PaymentServiceApplication {

  public static void main(final String[] args) {
    SpringApplication.run(PaymentServiceApplication.class, args);
  }

}
