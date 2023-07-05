package com.hotel.booking.system.hotel.service.core.domain.entity;

import com.hotel.booking.system.commons.core.domain.AbstractDomainEntity;
import com.hotel.booking.system.commons.core.message.ApplicationMessage;
import com.hotel.booking.system.hotel.service.core.domain.exception.HotelDomainException;
import com.hotel.booking.system.hotel.service.core.domain.valueobject.HotelId;
import com.hotel.booking.system.hotel.service.core.domain.valueobject.Money;
import com.hotel.booking.system.hotel.service.core.domain.valueobject.RoomId;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.StringUtils;

@SuperBuilder
public class Room extends AbstractDomainEntity<RoomId> {

  private HotelId hotelId;
  private String name;
  private String description;
  private Integer capacity;
  private Money currentPrice;
  private Integer quantity;

  public Room(
    final RoomId id,
    final HotelId hotelId,
    final String name,
    final String description,
    final Integer capacity,
    final Money currentPrice,
    final Integer quantity
  ) {
    super(id);
    this.hotelId = hotelId;
    this.name = name;
    this.description = description;
    this.capacity = capacity;
    this.currentPrice = currentPrice;
    this.quantity = quantity;
  }


  public void validate() {
    this.validateNameAndDescription();
    this.validateCapacity();
    this.validateCurrentPrice();
    this.validateHotel();
    this.validateQuantity();
  }

  private void validateQuantity() {
    if (this.quantity == null) {
      throw new HotelDomainException(ApplicationMessage.HOTEL_ROOM_QUANTITY_NOT_NULL);
    }
    if (this.quantity <= 0) {
      throw new HotelDomainException(ApplicationMessage.HOTEL_ROOM_QUANTITY_INVALID);
    }
  }

  private void validateHotel() {
    if (this.hotelId == null || this.hotelId.empty()) {
      throw new HotelDomainException(ApplicationMessage.HOTEL_ROOM_RELATIONSHIP_NOT_FOUND);
    }
  }

  private void validateNameAndDescription() {
    if (StringUtils.isBlank(this.name)) {
      throw new HotelDomainException(ApplicationMessage.HOTEL_ROOM_NAME_NOT_NULL);
    }
    if (StringUtils.isBlank(this.description)) {
      throw new HotelDomainException(ApplicationMessage.HOTEL_ROOM_DESCRIPTION_NOT_NULL);
    }
  }

  private void validateCapacity() {
    if (this.capacity == null) {
      throw new HotelDomainException(ApplicationMessage.HOTEL_ROOM_CAPACITY_NOT_NULL);
    }
    if (this.capacity <= 0) {
      throw new HotelDomainException(ApplicationMessage.HOTEL_ROOM_CAPACITY_INVALID);
    }
  }

  private void validateCurrentPrice() {
    if (this.currentPrice == null) {
      throw new HotelDomainException(ApplicationMessage.HOTEL_ROOM_CURRENT_PRICE_NOT_NULL);
    }
    if (this.currentPrice.isNegative() || this.currentPrice.isZero()) {
      throw new HotelDomainException(ApplicationMessage.HOTEL_ROOM_CURRENT_PRICE_INVALID);
    }
  }

  public void initialize(final HotelId hotelId) {
    this.setId(RoomId.newInstance());
    this.hotelId = hotelId;
  }

  public Money getCurrentPrice() {
    return this.currentPrice;
  }

  public String getName() {
    return this.name;
  }

  public String getDescription() {
    return this.description;
  }

  public Integer getCapacity() {
    return this.capacity;
  }

  public HotelId getHotelId() {
    return this.hotelId;
  }

  public Integer getQuantity() {
    return this.quantity;
  }

  public boolean hasCapacityFor(final Integer roomCapacity) {
    return this.capacity >= roomCapacity;
  }
}
