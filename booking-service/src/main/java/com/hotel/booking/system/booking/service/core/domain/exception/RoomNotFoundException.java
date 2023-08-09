package com.hotel.booking.system.booking.service.core.domain.exception;

import java.io.Serial;

public class RoomNotFoundException extends BookingDomainException {
  @Serial
  private static final long serialVersionUID = -6716457688391905042L;

  public RoomNotFoundException() {
  }

  public RoomNotFoundException(final String message) {
    super(message);
  }

  public RoomNotFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
