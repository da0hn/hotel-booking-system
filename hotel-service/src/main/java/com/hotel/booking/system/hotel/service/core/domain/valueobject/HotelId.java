package com.hotel.booking.system.hotel.service.core.domain.valueobject;


import com.hotel.booking.system.hotel.service.core.domain.AbstractDomainEntityId;

import java.util.UUID;

public class HotelId extends AbstractDomainEntityId<UUID> {
  protected HotelId(final UUID value) {
    super(value);
  }

  public static HotelId newInstance() {
    return new HotelId(UUID.randomUUID());
  }
}
