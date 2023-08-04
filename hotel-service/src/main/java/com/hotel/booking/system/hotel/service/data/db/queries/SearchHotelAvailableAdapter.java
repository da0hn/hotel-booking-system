package com.hotel.booking.system.hotel.service.data.db.queries;

import com.hotel.booking.system.commons.core.domain.valueobject.HotelId;
import com.hotel.booking.system.hotel.service.core.ports.spi.queries.SearchHotelAvailableQueryResult;
import com.hotel.booking.system.hotel.service.core.ports.spi.queries.SearchHotelAvailableRoomQueryResult;
import com.hotel.booking.system.hotel.service.data.db.entity.HotelEntity;

import java.util.List;
import java.util.stream.Collectors;

public class SearchHotelAvailableAdapter implements SearchHotelAvailableQueryResult {

  private final HotelEntity hotelEntity;

  public SearchHotelAvailableAdapter(final HotelEntity hotelEntity) {this.hotelEntity = hotelEntity;}

  @Override
  public HotelId getHotelId() {
    return HotelId.of(this.hotelEntity.getId());
  }

  @Override
  public String getHotelName() {
    return this.hotelEntity.getName();
  }

  @Override
  public String getHotelDescription() {
    return this.hotelEntity.getDescription();
  }

  @Override
  public String getHotelCep() {
    return this.hotelEntity.getHotelCep();
  }

  @Override
  public String getHotelStreet() {
    return this.hotelEntity.getHotelStreet();
  }

  @Override
  public String getHotelCategoryName() {
    return this.hotelEntity.getCategory().getName();
  }

  @Override
  public String getHotelCity() {
    return this.hotelEntity.getLocality().getCity();

  }

  @Override
  public String getHotelState() {
    return this.hotelEntity.getLocality().getState();
  }

  @Override
  public String getHotelCountry() {
    return this.hotelEntity.getLocality().getCountry();
  }

  @Override
  public List<SearchHotelAvailableRoomQueryResult> getRooms() {
    return this.hotelEntity.getRooms().stream()
      .map(SearchHotelAvailableRoomAdapter::new)
      .collect(Collectors.toList());
  }
}
