package com.hotel.booking.system.commons.core.domain.event.customer;

import com.hotel.booking.system.commons.core.domain.valueobject.CustomerReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.Instant;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public final class CustomerBookingRejectedEvent extends CustomerBookingStatusUpdatedEvent {

  private final Instant createdAt = Instant.now();
  private final String reservationOrderId;
  private final String customerId;
  private final List<String> failureMessages;
  private final CustomerReservationStatus status;

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }

}