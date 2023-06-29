package com.hotel.booking.system.hotel.service.core.domain.valueobject;


import com.hotel.booking.system.commons.core.domain.AbstractDomainEntityId;
import com.hotel.booking.system.commons.core.message.ApplicationMessage;

import java.util.Objects;
import java.util.UUID;

public class RoomId extends AbstractDomainEntityId<UUID> {
  private RoomId(final UUID value) {
    super(value);
  }

  public static RoomId newInstance() {
    return new RoomId(UUID.randomUUID());
  }

  public static RoomId of(UUID value) {
    Objects.requireNonNull(value, ApplicationMessage.HOTEL_ROOM_NOT_NULL);
    return new RoomId(value);
  }

  @Override
  public String toString() {
    return this.value.toString();
  }

}
