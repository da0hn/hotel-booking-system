package com.hotel.booking.system.booking.service.data.db.repository.adapters;

import com.hotel.booking.system.booking.service.core.domain.exception.RoomNotFoundException;
import com.hotel.booking.system.booking.service.data.db.entity.RoomEntity;
import com.hotel.booking.system.booking.service.data.db.repository.RoomJpaRepository;
import com.hotel.booking.system.commons.core.domain.valueobject.RoomId;
import com.hotel.booking.system.commons.core.message.ApplicationMessage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RoomRepositoryAdapter {

    private final RoomJpaRepository roomJpaRepository;

    public RoomEntity findRoomEntityById(final RoomId roomId) {
        return this.roomJpaRepository.findById(roomId.getValue())
          .orElseThrow(() -> new RoomNotFoundException(ApplicationMessage.HOTEL_ROOM_NOT_FOUND));
    }

}
