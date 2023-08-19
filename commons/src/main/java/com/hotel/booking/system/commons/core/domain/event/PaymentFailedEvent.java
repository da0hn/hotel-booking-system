package com.hotel.booking.system.commons.core.domain.event;

import com.hotel.booking.system.commons.core.domain.valueobject.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public final class PaymentFailedEvent extends PaymentResponseEvent {

  @Builder.Default
  private final Instant createdAt = Instant.now();
  private final String reservationOrderId;
  private final String customerId;
  private final BigDecimal totalPrice;
  private final PaymentStatus status;
  private final List<String> failureMessages;

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
