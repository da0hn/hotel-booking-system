package com.hotel.booking.system.hotel.service.core.domain.entity;


import com.hotel.booking.system.commons.core.domain.AbstractDomainEntity;
import com.hotel.booking.system.commons.core.message.ApplicationMessage;
import com.hotel.booking.system.hotel.service.core.domain.exception.HotelDomainException;
import com.hotel.booking.system.hotel.service.core.domain.valueobject.HotelAddress;
import com.hotel.booking.system.hotel.service.core.domain.valueobject.HotelCategoryId;
import com.hotel.booking.system.hotel.service.core.domain.valueobject.HotelId;
import com.hotel.booking.system.hotel.service.core.domain.valueobject.LocalityId;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

@SuperBuilder
public class Hotel extends AbstractDomainEntity<HotelId> {

  private final String name;
  private final String description;
  private final HotelAddress address;
  private final HotelCategoryId categoryId;
  private final LocalityId localityId;
  private final Rooms rooms;

  public Hotel(
    final HotelId id,
    final String name,
    final String description,
    final HotelCategoryId categoryId,
    final HotelAddress address,
    final LocalityId localityId,
    final Rooms rooms
  ) {
    super(id);
    this.name = name;
    this.description = description;
    this.categoryId = categoryId;
    this.address = address;
    this.localityId = localityId;
    this.rooms = rooms;
  }

  public void validate() {
    this.validateNameAndDescription();
    this.validateCategory();
    this.validateLocality();
    this.validateAddress();
    this.validateRooms();
  }

  private void validateRooms() {
    if (CollectionUtils.isEmpty(this.rooms)) {
      throw new HotelDomainException(ApplicationMessage.HOTEL_ROOMS_INVALID);
    }
    this.rooms.validate();
  }

  public void initialize() {
    this.setId(HotelId.newInstance());
    this.rooms.forEach(room -> room.initialize(this.getId()));
  }

  private void validateNameAndDescription() {
    if (StringUtils.isBlank(this.name)) {
      throw new HotelDomainException(ApplicationMessage.HOTEL_NAME_INVALID);
    }
    if (StringUtils.isBlank(this.description)) {
      throw new HotelDomainException(ApplicationMessage.HOTEL_DESCRIPTION_INVALID);
    }
  }

  private void validateAddress() {
    this.address.validate();
  }

  private void validateLocality() {
    if (this.localityId == null || this.localityId.empty()) {
      throw new HotelDomainException(ApplicationMessage.HOTEL_LOCALITY_NOT_NULL);
    }
  }

  private void validateCategory() {
    if (this.categoryId == null || this.categoryId.empty()) {
      throw new HotelDomainException(ApplicationMessage.HOTEL_CATEGORY_NOT_NULL);
    }
  }

  public Rooms getRoom() {
    return this.rooms;
  }

  public LocalityId getLocalityId() {
    return this.localityId;
  }

  public HotelCategoryId getCategoryId() {
    return this.categoryId;
  }

  public HotelAddress getAddress() {
    return this.address;
  }

  public String getName() {
    return this.name;
  }

  public String getDescription() {
    return this.description;
  }

}

