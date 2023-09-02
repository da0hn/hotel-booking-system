package com.hotel.booking.system.booking.service.data.db.repository.adapters;

import com.hotel.booking.system.booking.service.core.domain.entity.Room;
import com.hotel.booking.system.booking.service.core.domain.exception.RoomNotFoundException;
import com.hotel.booking.system.booking.service.core.ports.spi.repository.RoomRepository;
import com.hotel.booking.system.booking.service.data.db.entity.RoomEntity;
import com.hotel.booking.system.booking.service.data.db.mapper.BookingDatabaseMapper;
import com.hotel.booking.system.booking.service.data.db.repository.RoomJpaRepository;
import com.hotel.booking.system.commons.core.domain.valueobject.RoomId;
import com.hotel.booking.system.commons.core.message.ApplicationMessage;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoomRepositoryAdapter implements RoomRepository {

  private final RoomJpaRepository roomJpaRepository;
  private final BookingDatabaseMapper bookingDatabaseMapper;

  public RoomRepositoryAdapter(final RoomJpaRepository roomJpaRepository, @Lazy final BookingDatabaseMapper bookingDatabaseMapper) {
    this.roomJpaRepository = roomJpaRepository;
    this.bookingDatabaseMapper = bookingDatabaseMapper;
  }

  public RoomEntity findRoomEntityById(final RoomId roomId) {
    return this.roomJpaRepository.findById(roomId.getValue())
      .orElseThrow(() -> new RoomNotFoundException(ApplicationMessage.HOTEL_ROOM_NOT_FOUND));
  }

  @Override
  public List<Room> findAllByRoomId(final Collection<? extends RoomId> ids) {
    return this.roomJpaRepository.findAllById(ids.stream()
        .map(RoomId::getValue)
        .collect(Collectors.toSet())
      ).stream()
      .map(this.bookingDatabaseMapper::roomEntityToRoom)
      .collect(Collectors.toList());
  }
}
