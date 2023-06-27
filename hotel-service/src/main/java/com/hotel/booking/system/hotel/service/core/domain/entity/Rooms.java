package com.hotel.booking.system.hotel.service.core.domain.entity;

import com.hotel.booking.system.commons.core.domain.AbstractDomainList;
import com.hotel.booking.system.hotel.service.core.domain.valueobject.RoomId;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Rooms extends AbstractDomainList<Room> {

  private Rooms(final List<Room> data) {
    super(data);
  }

  public static Rooms empty() {
    return new Rooms(new ArrayList<>());
  }

  public static Rooms newInstance(final List<Room> rooms) {
    return new Rooms(rooms);
  }

  public void validate() {
    this.data().forEach(Room::validate);
  }

  public List<String> asRawIds() {
    return this.data().stream()
      .map(Room::getId)
      .map(RoomId::toString)
      .collect(Collectors.toList());
  }
}
