package com.hotel.booking.system.hotel.service.core.domain.exception;

import java.io.Serial;

public final class HotelDomainException extends RuntimeException {
  @Serial
  private static final long serialVersionUID = -8900806412303535160L;

  public HotelDomainException(final String message) {
    super(message);
  }

  public HotelDomainException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
