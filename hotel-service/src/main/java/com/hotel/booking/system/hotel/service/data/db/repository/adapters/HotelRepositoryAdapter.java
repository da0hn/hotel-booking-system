package com.hotel.booking.system.hotel.service.data.db.repository.adapters;

import com.hotel.booking.system.hotel.service.core.domain.entity.Hotel;
import com.hotel.booking.system.hotel.service.core.domain.valueobject.HotelCategoryId;
import com.hotel.booking.system.hotel.service.core.ports.spi.queries.SearchHotelAvailableQueryResult;
import com.hotel.booking.system.hotel.service.core.ports.spi.repository.HotelRepository;
import com.hotel.booking.system.hotel.service.data.db.mapper.HotelDatabaseMapper;
import com.hotel.booking.system.hotel.service.data.db.repository.HotelCategoryJpaRepository;
import com.hotel.booking.system.hotel.service.data.db.repository.HotelJpaRepository;
import com.hotel.booking.system.hotel.service.data.db.repository.RoomJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class HotelRepositoryAdapter implements HotelRepository {

  private final HotelJpaRepository hotelJpaRepository;
  private final HotelCategoryJpaRepository hotelCategoryJpaRepository;
  private final RoomJpaRepository roomJpaRepository;
  private final HotelDatabaseMapper hotelDatabaseMapper;

  @Override
  public void register(final Hotel newHotel) {
    final var newHotelEntity = this.hotelDatabaseMapper.hotelToHotelEntity(newHotel);
    this.hotelJpaRepository.save(newHotelEntity);
    final var newRooms = this.hotelDatabaseMapper.roomsToRoomEntitySet(newHotel.getRoom());
    this.roomJpaRepository.saveAll(newRooms);
  }

  @Override
  public boolean existsCategoryById(final HotelCategoryId hotelCategoryId) {
    return this.hotelCategoryJpaRepository.existsById(hotelCategoryId.getValue());
  }

  @Override
  public List<SearchHotelAvailableQueryResult> searchHotelAvailableBy(
    final String name,
    final String category,
    final String city,
    final String state
  ) {
    return this.hotelJpaRepository.findAllAvailableHotelByParameters(name, category, city, state).stream()
      .map(this.hotelDatabaseMapper::hotelEntityToSearchHotelAvailableQueryResult)
      .toList();
  }
}
