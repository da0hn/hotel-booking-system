package com.hotel.booking.system.hotel.service.core.ports.spi;

import com.hotel.booking.system.hotel.service.core.domain.entity.Hotel;
import com.hotel.booking.system.hotel.service.core.domain.valueobject.HotelCategoryId;

public interface HotelRepository {

  void register(Hotel newHotel);

  boolean existsCategoryById(HotelCategoryId hotelCategoryId);
}
