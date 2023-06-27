package com.hotel.booking.system.hotel.service.core.domain.valueobject;

import com.hotel.booking.system.hotel.service.core.domain.AbstractDomainEntityId;

import java.util.UUID;

public class LocalityId extends AbstractDomainEntityId<UUID> {
  protected LocalityId(final UUID value) {
    super(value);
  }

  public static LocalityId of(final String rawValue) {
    return new LocalityId(UUID.fromString(rawValue));
  }
}
