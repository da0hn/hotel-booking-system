package com.hotel.booking.system.hotel.service.core.ports.spi.repository;

import com.hotel.booking.system.commons.core.domain.valueobject.RoomId;
import com.hotel.booking.system.hotel.service.core.domain.entity.Hotel;
import com.hotel.booking.system.hotel.service.core.domain.entity.Rooms;
import com.hotel.booking.system.hotel.service.core.domain.valueobject.HotelCategoryId;
import com.hotel.booking.system.hotel.service.core.ports.spi.queries.SearchHotelAvailableQueryResult;

import java.util.List;

public interface HotelRepository {

  void register(Hotel newHotel);

  boolean existsCategoryById(HotelCategoryId hotelCategoryId);

  List<SearchHotelAvailableQueryResult> searchHotelAvailableBy(String name, String category, String city, String state);

  Rooms findAllRoomsById(List<? extends RoomId> roomIds);
}
