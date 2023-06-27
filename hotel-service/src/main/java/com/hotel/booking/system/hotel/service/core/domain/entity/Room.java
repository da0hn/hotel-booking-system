package com.hotel.booking.system.hotel.service.core.domain.entity;

import com.hotel.booking.system.commons.core.domain.AbstractDomainEntity;
import com.hotel.booking.system.hotel.service.core.domain.valueobject.HotelId;
import com.hotel.booking.system.hotel.service.core.domain.valueobject.Money;
import com.hotel.booking.system.hotel.service.core.domain.valueobject.RoomId;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class Room extends AbstractDomainEntity<RoomId> {

  private HotelId hotelId;
  private String name;
  private String description;
  private Integer capacity;
  private Money currentPrice;

  public Room(
    final RoomId id,
    final HotelId hotelId,
    final String name,
    final String description,
    final Integer capacity,
    final Money currentPrice
  ) {
    super(id);
    this.hotelId = hotelId;
    this.name = name;
    this.description = description;
    this.capacity = capacity;
    this.currentPrice = currentPrice;
  }


  public void validate() {
  }

  public void initialize(final HotelId hotelId) {
    this.setId(RoomId.newInstance());
    this.hotelId = hotelId;
  }
}
