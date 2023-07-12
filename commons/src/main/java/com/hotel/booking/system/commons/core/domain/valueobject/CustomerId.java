package com.hotel.booking.system.commons.core.domain.valueobject;

import com.hotel.booking.system.commons.core.domain.AbstractDomainEntityId;
import com.hotel.booking.system.commons.core.message.ApplicationMessage;

import java.util.Objects;
import java.util.UUID;

public class CustomerId extends AbstractDomainEntityId<UUID> {
  protected CustomerId(final UUID value) {
    super(value);
  }

  public static CustomerId newInstance() {
    return new CustomerId(UUID.randomUUID());
  }

  public static CustomerId of(final UUID value) {
    Objects.requireNonNull(value, ApplicationMessage.HOTEL_ROOM_NOT_NULL);
    return new CustomerId(value);
  }

  public static CustomerId of(final String rawValue) {
    Objects.requireNonNull(rawValue, ApplicationMessage.HOTEL_ROOM_NOT_NULL);
    return new CustomerId(UUID.fromString(rawValue));
  }

}
