package com.hotel.booking.system.hotel.service.core.domain.valueobject;


import com.hotel.booking.system.commons.core.domain.AbstractDomainEntityId;
import com.hotel.booking.system.commons.core.message.ApplicationMessage;

import java.util.Objects;
import java.util.UUID;

public class HotelCategoryId extends AbstractDomainEntityId<UUID> {
  private HotelCategoryId(final UUID value) {
    super(value);
  }

  public static HotelCategoryId of(final String rawValue) {
    Objects.requireNonNull(rawValue, ApplicationMessage.HOTEL_CATEGORY_NOT_NULL);
    return new HotelCategoryId(UUID.fromString(rawValue));
  }

  @Override
  public String toString() {
    return this.value.toString();
  }
}
