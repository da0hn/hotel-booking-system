package com.hotel.booking.system.hotel.service.core.domain.valueobject;

import com.hotel.booking.system.hotel.service.core.domain.AbstractDomainEntityId;

import java.util.UUID;

public class RoomId extends AbstractDomainEntityId<UUID> {
  private RoomId(final UUID value) {
    super(value);
  }

  public static RoomId newInstance() {
    return new RoomId(UUID.randomUUID());
  }

  @Override
  public String toString() {
    return this.value.toString();
  }

}
