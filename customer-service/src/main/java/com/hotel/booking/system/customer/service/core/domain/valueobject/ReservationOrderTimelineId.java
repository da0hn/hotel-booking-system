package com.hotel.booking.system.customer.service.core.domain.valueobject;

import com.hotel.booking.system.commons.core.domain.AbstractDomainEntityId;
import com.hotel.booking.system.commons.core.message.ApplicationMessage;

import java.util.Objects;
import java.util.UUID;

public class ReservationOrderTimelineId extends AbstractDomainEntityId<UUID> {

  protected ReservationOrderTimelineId(final UUID value) {
    super(value);
  }

  public static ReservationOrderTimelineId newInstance() {
    return new ReservationOrderTimelineId(UUID.randomUUID());
  }

  public static ReservationOrderTimelineId of(final UUID value) {
    Objects.requireNonNull(value, ApplicationMessage.CUSTOMER_RESERVATION_ORDER_TIMELINE_NOT_NULL);
    return new ReservationOrderTimelineId(value);
  }

  public static ReservationOrderTimelineId of(final String rawValue) {
    Objects.requireNonNull(rawValue, ApplicationMessage.CUSTOMER_RESERVATION_ORDER_TIMELINE_NOT_NULL);
    return new ReservationOrderTimelineId(UUID.fromString(rawValue));
  }

  @Override
  public String toString() {
    return this.value.toString();
  }
}
