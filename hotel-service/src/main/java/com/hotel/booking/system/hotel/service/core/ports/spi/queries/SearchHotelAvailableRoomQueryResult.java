package com.hotel.booking.system.hotel.service.core.ports.spi.queries;

import com.hotel.booking.system.hotel.service.core.domain.valueobject.Money;
import com.hotel.booking.system.hotel.service.core.domain.valueobject.RoomId;


public interface SearchHotelAvailableRoomQueryResult {

  RoomId getRoomId();

  String getRoomName();

  String getRoomDescription();

  Integer getRoomCapacity();

  Money getRoomCurrentPrice();

  Integer getRoomQuantity();

}
