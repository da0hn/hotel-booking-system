package com.hotel.booking.system.commons.core.domain.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Builder
@AllArgsConstructor
public class PaymentRequestedEvent {

  private final Instant createdAt = Instant.now();
  private final String bookingRoomId;
  private final String reservationOrderId;
  private final String customerId;
  private final BigDecimal totalPrice;

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
