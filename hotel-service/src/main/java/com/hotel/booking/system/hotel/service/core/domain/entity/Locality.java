package com.hotel.booking.system.hotel.service.core.domain.entity;

import com.hotel.booking.system.commons.core.domain.AbstractDomainEntity;
import com.hotel.booking.system.hotel.service.core.domain.valueobject.LocalityId;

public class Locality extends AbstractDomainEntity<LocalityId> {

  private final String country;
  private final String state;
  private final String city;


  public Locality(final LocalityId id, final String city, final String state, final String country) {
    super(id);
    this.city = city;
    this.state = state;
    this.country = country;
  }
}
