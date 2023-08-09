package com.hotel.booking.system.booking.service.core.domain.entity;

import com.hotel.booking.system.commons.core.domain.AbstractDomainEntity;
import com.hotel.booking.system.commons.core.domain.valueobject.HotelId;
import com.hotel.booking.system.commons.core.domain.valueobject.RoomId;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Room extends AbstractDomainEntity<RoomId> {

    private HotelId hotelId;
    private Integer capacity;
    private Integer quantity;

    public Room(
      final RoomId id,
      final HotelId hotelId,
      final Integer capacity,
      final Integer quantity
    ) {
        super(id);
        this.hotelId = hotelId;
        this.capacity = capacity;
        this.quantity = quantity;
    }

}
