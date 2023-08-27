package com.hotel.booking.system.customer.service.core.domain.exception;

import com.hotel.booking.system.commons.core.message.ApplicationMessage;

import java.io.Serial;

public class CustomerNotFoundException extends CustomerDomainException {
  @Serial
  private static final long serialVersionUID = -1641830298069605965L;

  public CustomerNotFoundException() {
    super(ApplicationMessage.CUSTOMER_NOT_FOUND);
  }

  public CustomerNotFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
