package com.hotel.booking.system.hotel.service.core.ports.spi.queries;

import com.hotel.booking.system.commons.core.domain.valueobject.HotelId;

import java.util.List;

public interface SearchHotelAvailableQueryResult {

  HotelId getHotelId();

  String getHotelName();

  String getHotelDescription();

  String getHotelCep();

  String getHotelStreet();

  String getHotelCategoryName();

  String getHotelCity();

  String getHotelState();

  String getHotelCountry();

  List<SearchHotelAvailableRoomQueryResult> getRooms();

}
