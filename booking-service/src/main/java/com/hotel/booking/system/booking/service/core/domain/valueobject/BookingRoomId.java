package com.hotel.booking.system.booking.service.core.domain.valueobject;

import com.hotel.booking.system.commons.core.domain.AbstractDomainEntityId;
import com.hotel.booking.system.commons.core.message.ApplicationMessage;

import java.util.Objects;
import java.util.UUID;

public class BookingRoomId extends AbstractDomainEntityId<UUID> {
  private BookingRoomId(final UUID value) {
    super(value);
  }

  public static BookingRoomId newInstance() {
    return new BookingRoomId(UUID.randomUUID());
  }

  public static BookingRoomId of(final String rawValue) {
    Objects.requireNonNull(rawValue, ApplicationMessage.BOOKING_ROOM_NOT_NULL);
    return new BookingRoomId(UUID.fromString(rawValue));
  }

  public static BookingRoomId of(final UUID value) {
    Objects.requireNonNull(value, ApplicationMessage.BOOKING_ROOM_NOT_NULL);
    return new BookingRoomId(value);
  }

  @Override
  public String toString() {
    return this.value.toString();
  }
}
