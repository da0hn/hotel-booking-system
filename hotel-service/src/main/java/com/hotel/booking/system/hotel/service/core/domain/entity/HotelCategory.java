package com.hotel.booking.system.hotel.service.core.domain.entity;

import com.hotel.booking.system.commons.core.domain.AbstractDomainEntity;
import com.hotel.booking.system.hotel.service.core.domain.valueobject.HotelCategoryId;

public class HotelCategory extends AbstractDomainEntity<HotelCategoryId> {

  private final String name;
  private final String description;

  protected HotelCategory(final HotelCategoryId id, final String name, final String description) {
    super(id);
    this.name = name;
    this.description = description;
  }

}
