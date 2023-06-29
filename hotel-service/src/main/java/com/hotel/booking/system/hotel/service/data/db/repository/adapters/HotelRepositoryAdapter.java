package com.hotel.booking.system.hotel.service.data.db.repository.adapters;

import com.hotel.booking.system.hotel.service.core.domain.entity.Hotel;
import com.hotel.booking.system.hotel.service.core.domain.valueobject.HotelCategoryId;
import com.hotel.booking.system.hotel.service.core.ports.spi.HotelRepository;
import com.hotel.booking.system.hotel.service.data.db.mapper.HotelDatabaseMapper;
import com.hotel.booking.system.hotel.service.data.db.repository.HotelJpaRepository;
import com.hotel.booking.system.hotel.service.data.db.repository.RoomJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class HotelRepositoryAdapter implements HotelRepository {

  private final HotelJpaRepository hotelJpaRepository;
  private final RoomJpaRepository roomJpaRepository;
  private final HotelDatabaseMapper hotelDatabaseMapper;

  @Override
  public void register(final Hotel newHotel) {
    final var newHotelEntity = this.hotelDatabaseMapper.hotelToHotelEntity(newHotel);
    this.hotelJpaRepository.save(newHotelEntity);
    this.roomJpaRepository.saveAll(newHotelEntity.getRooms());
  }

  @Override
  public boolean existsCategoryById(final HotelCategoryId hotelCategoryId) {
    return this.hotelJpaRepository.existsByCategoryId(hotelCategoryId.getValue());
  }
}
