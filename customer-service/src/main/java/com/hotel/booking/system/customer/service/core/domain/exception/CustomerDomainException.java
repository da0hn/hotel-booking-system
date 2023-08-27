package com.hotel.booking.system.customer.service.core.domain.exception;

import java.io.Serial;

public class CustomerDomainException extends RuntimeException {
  @Serial
  private static final long serialVersionUID = 1388861129307014297L;

  public CustomerDomainException(final String message) {
    super(message);
  }

  public CustomerDomainException(final String message, final Throwable cause) {
    super(message, cause);
  }

}
