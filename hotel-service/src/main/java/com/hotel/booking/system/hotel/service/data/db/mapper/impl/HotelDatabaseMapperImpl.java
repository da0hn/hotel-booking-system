package com.hotel.booking.system.hotel.service.data.db.mapper.impl;

import com.hotel.booking.system.hotel.service.core.domain.entity.Hotel;
import com.hotel.booking.system.hotel.service.core.domain.entity.Room;
import com.hotel.booking.system.hotel.service.core.domain.entity.Rooms;
import com.hotel.booking.system.hotel.service.core.domain.valueobject.*;
import com.hotel.booking.system.hotel.service.data.db.entity.HotelCategoryEntity;
import com.hotel.booking.system.hotel.service.data.db.entity.HotelEntity;
import com.hotel.booking.system.hotel.service.data.db.entity.LocalityEntity;
import com.hotel.booking.system.hotel.service.data.db.entity.RoomEntity;
import com.hotel.booking.system.hotel.service.data.db.mapper.HotelDatabaseMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class HotelDatabaseMapperImpl implements HotelDatabaseMapper {

  @Override
  public Hotel hotelEntityToHotel(final HotelEntity hotelEntity) {
    return Hotel.builder()
      .id(HotelId.of(hotelEntity.getId()))
      .name(hotelEntity.getName())
      .description(hotelEntity.getDescription())
      .categoryId(HotelCategoryId.of(hotelEntity.getCategory().getId()))
      .localityId(LocalityId.of(hotelEntity.getId()))
      .address(new HotelAddress(hotelEntity.getHotelCep(), hotelEntity.getHotelStreet()))
      .rooms(Rooms.newInstance(
        hotelEntity.getRooms().stream()
          .map(this::roomEntityToRoom)
          .toList()
      ))
      .build();
  }

  @Override
  public HotelEntity hotelToHotelEntity(final Hotel hotel) {
    return HotelEntity.builder()
      .id(hotel.getId().getValue())
      .hotelCep(hotel.getAddress().getCep())
      .hotelStreet(hotel.getAddress().getStreet())
      .name(hotel.getName())
      .description(hotel.getDescription())
      .category(
        HotelCategoryEntity.builder()
          .id(hotel.getCategoryId().getValue())
          .build()
      )
      .locality(
        LocalityEntity.builder()
          .id(hotel.getLocalityId().getValue())
          .build()
      )
      .build();
  }

  @Override
  public Room roomEntityToRoom(final RoomEntity room) {
    return Room.builder()
      .id(RoomId.of(room.getId()))
      .hotelId(HotelId.of(room.getHotel().getId()))
      .name(room.getName())
      .description(room.getDescription())
      .currentPrice(Money.of(room.getCurrentPrice()))
      .capacity(room.getCapacity())
      .build();
  }

  @Override
  public RoomEntity roomToRoomEntity(final Room room) {
    return RoomEntity.builder()
      .id(room.getId().getValue())
      .currentPrice(room.getCurrentPrice().getValue())
      .name(room.getName())
      .description(room.getDescription())
      .capacity(room.getCapacity())
      .hotel(
        HotelEntity.builder()
          .id(room.getHotelId().getValue())
          .build()
      )
      .build();
  }

  @Override
  public Set<RoomEntity> roomsToRoomEntitySet(final Collection<? extends Room> rooms) {
    return rooms.stream()
      .map(this::roomToRoomEntity)
      .collect(Collectors.toSet());
  }
}
