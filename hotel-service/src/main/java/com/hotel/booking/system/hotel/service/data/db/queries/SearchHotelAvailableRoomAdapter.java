package com.hotel.booking.system.hotel.service.data.db.queries;

import com.hotel.booking.system.hotel.service.core.domain.valueobject.Money;
import com.hotel.booking.system.hotel.service.core.domain.valueobject.RoomId;
import com.hotel.booking.system.hotel.service.core.ports.spi.queries.SearchHotelAvailableRoomQueryResult;
import com.hotel.booking.system.hotel.service.data.db.entity.RoomEntity;

public class SearchHotelAvailableRoomAdapter implements SearchHotelAvailableRoomQueryResult {

  private final RoomEntity roomEntity;

  public SearchHotelAvailableRoomAdapter(final RoomEntity roomEntity) {this.roomEntity = roomEntity;}

  @Override
  public RoomId getRoomId() {
    return RoomId.of(this.roomEntity.getId());
  }

  @Override
  public String getRoomName() {
    return this.roomEntity.getName();
  }

  @Override
  public String getRoomDescription() {
    return this.roomEntity.getDescription();
  }

  @Override
  public Integer getRoomCapacity() {
    return this.roomEntity.getCapacity();
  }

  @Override
  public Money getRoomCurrentPrice() {
    return Money.of(this.roomEntity.getCurrentPrice());
  }

  @Override
  public Integer getRoomQuantity() {
    return this.roomEntity.getCapacity();
  }
}
