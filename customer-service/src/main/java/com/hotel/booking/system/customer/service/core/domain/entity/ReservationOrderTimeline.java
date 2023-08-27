package com.hotel.booking.system.customer.service.core.domain.entity;

import com.hotel.booking.system.commons.core.domain.AbstractDomainEntity;
import com.hotel.booking.system.commons.core.domain.valueobject.CustomerReservationStatus;
import com.hotel.booking.system.customer.service.core.domain.valueobject.ReservationOrderTimelineId;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Getter
@SuperBuilder
public class ReservationOrderTimeline extends AbstractDomainEntity<ReservationOrderTimelineId> {

  private CustomerReservationStatus status;
  private Instant occurredAt;

  public ReservationOrderTimeline(
    final ReservationOrderTimelineId id,
    final CustomerReservationStatus status,
    final Instant occurredAt
  ) {
    super(id);
    this.status = status;
    this.occurredAt = occurredAt;
  }

  public static ReservationOrderTimeline update(final CustomerReservationStatus status) {
    return ReservationOrderTimeline.builder()
      .id(ReservationOrderTimelineId.newInstance())
      .status(status)
      .occurredAt(Instant.now())
      .build();
  }

}
