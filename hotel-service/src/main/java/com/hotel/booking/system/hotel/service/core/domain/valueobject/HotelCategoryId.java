package com.hotel.booking.system.hotel.service.core.domain.valueobject;

import com.hotel.booking.system.hotel.service.core.domain.AbstractDomainEntityId;

import java.util.UUID;

public class HotelCategoryId extends AbstractDomainEntityId<UUID> {
  private HotelCategoryId(final UUID value) {
    super(value);
  }

  public static HotelCategoryId of(final String rawValue) {
    return new HotelCategoryId(UUID.fromString(rawValue));
  }
}
