package com.hotel.booking.system.hotel.service.core.domain.valueobject;


import com.hotel.booking.system.commons.core.domain.AbstractDomainEntityId;
import com.hotel.booking.system.commons.core.message.ApplicationMessage;

import java.util.Objects;
import java.util.UUID;

public class HotelId extends AbstractDomainEntityId<UUID> {
  private HotelId(final UUID value) {
    super(value);
  }

  public static HotelId newInstance() {
    return new HotelId(UUID.randomUUID());
  }

  public static HotelId of(final String rawValue) {
    Objects.requireNonNull(rawValue, ApplicationMessage.HOTEL_NOT_NULL);
    return new HotelId(UUID.fromString(rawValue));
  }

  @Override
  public String toString() {
    return this.value.toString();
  }
}
