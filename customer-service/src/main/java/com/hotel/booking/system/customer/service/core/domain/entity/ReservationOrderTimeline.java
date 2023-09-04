package com.hotel.booking.system.customer.service.core.domain.entity;

import com.hotel.booking.system.commons.core.domain.AbstractDomainEntity;
import com.hotel.booking.system.commons.core.domain.valueobject.CustomerReservationStatus;
import com.hotel.booking.system.commons.core.domain.valueobject.FailureMessages;
import com.hotel.booking.system.customer.service.core.domain.valueobject.ReservationOrderTimelineId;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Getter
@SuperBuilder
public class ReservationOrderTimeline extends AbstractDomainEntity<ReservationOrderTimelineId> {

  private CustomerReservationStatus status;
  private String reason;
  private Instant occurredAt;

  public ReservationOrderTimeline(
    final ReservationOrderTimelineId id,
    final CustomerReservationStatus status,
    final String reason,
    final Instant occurredAt
  ) {
    super(id);
    this.status = status;
    this.reason = reason;
    this.occurredAt = occurredAt;
  }

  public static ReservationOrderTimeline update(final CustomerReservationStatus status) {
    return ReservationOrderTimeline.builder()
      .id(ReservationOrderTimelineId.newInstance())
      .status(status)
      .occurredAt(Instant.now())
      .build();
  }

  public static ReservationOrderTimeline update(final CustomerReservationStatus status, final FailureMessages failureMessages) {
    final var failureReason = String.join("\n", failureMessages);
    return ReservationOrderTimeline.builder()
      .id(ReservationOrderTimelineId.newInstance())
      .status(status)
      .reason(failureReason)
      .occurredAt(Instant.now())
      .build();
  }

}
