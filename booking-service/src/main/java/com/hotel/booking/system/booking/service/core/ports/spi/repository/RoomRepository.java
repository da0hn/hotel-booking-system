package com.hotel.booking.system.booking.service.core.ports.spi.repository;

import com.hotel.booking.system.booking.service.core.domain.entity.Room;
import com.hotel.booking.system.commons.core.domain.valueobject.RoomId;

import java.util.Collection;
import java.util.List;

public interface RoomRepository {

  List<Room> findAllByRoomId(Collection<? extends RoomId> ids);

}
