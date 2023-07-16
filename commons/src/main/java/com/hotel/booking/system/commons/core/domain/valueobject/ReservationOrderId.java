package com.hotel.booking.system.commons.core.domain.valueobject;

import com.hotel.booking.system.commons.core.domain.AbstractDomainEntityId;

import java.util.UUID;

public class ReservationOrderId extends AbstractDomainEntityId<UUID> {
  protected ReservationOrderId(final UUID value) {
    super(value);
  }

  public static ReservationOrderId newInstance() {
    return new ReservationOrderId(UUID.randomUUID());
  }

  public static ReservationOrderId of(final UUID value) {
    return new ReservationOrderId(value);
  }

  public static ReservationOrderId of(final String rawValue) {
    return new ReservationOrderId(UUID.fromString(rawValue));
  }

  @Override
  public String toString() {
    return this.value.toString();
  }
}
