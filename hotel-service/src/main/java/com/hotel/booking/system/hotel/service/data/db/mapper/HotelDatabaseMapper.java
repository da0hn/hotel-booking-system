package com.hotel.booking.system.hotel.service.data.db.mapper;

import com.hotel.booking.system.hotel.service.core.domain.entity.Hotel;
import com.hotel.booking.system.hotel.service.core.domain.entity.Room;
import com.hotel.booking.system.hotel.service.data.db.entity.HotelEntity;
import com.hotel.booking.system.hotel.service.data.db.entity.RoomEntity;

import java.util.Collection;
import java.util.Set;

public interface HotelDatabaseMapper {

  Hotel hotelEntityToHotel(HotelEntity hotelEntity);

  HotelEntity hotelToHotelEntity(Hotel hotel);

  Room roomEntityToRoom(RoomEntity room);

  RoomEntity roomToRoomEntity(Room room);

  Set<RoomEntity> roomsToRoomEntitySet(Collection<? extends Room> rooms);

}
