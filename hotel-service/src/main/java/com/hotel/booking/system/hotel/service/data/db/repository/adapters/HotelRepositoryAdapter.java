package com.hotel.booking.system.hotel.service.data.db.repository.adapters;

import com.hotel.booking.system.hotel.service.core.domain.entity.Hotel;
import com.hotel.booking.system.hotel.service.core.domain.valueobject.HotelCategoryId;
import com.hotel.booking.system.hotel.service.core.ports.spi.HotelRepository;
import com.hotel.booking.system.hotel.service.data.db.repository.HotelJpaRepository;
import com.hotel.booking.system.hotel.service.data.db.repository.RoomJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class HotelRepositoryAdapter implements HotelRepository {

  private final HotelJpaRepository hotelJpaRepository;
  private final RoomJpaRepository roomJpaRepository;

  @Override
  public void register(final Hotel newHotel) {

  }

  @Override
  public boolean existsCategoryById(final HotelCategoryId hotelCategoryId) {
    return false;
  }
}
