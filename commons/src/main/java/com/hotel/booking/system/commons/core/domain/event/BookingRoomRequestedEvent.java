package com.hotel.booking.system.commons.core.domain.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class BookingRoomRequestedEvent implements Event {

  @Builder.Default
  private final Instant createdAt = Instant.now();
  private final String reservationOrderId;
  private final String customerId;
  private final BigDecimal price;
  private final Integer guests;
  private final LocalDate checkIn;
  private final LocalDate checkOut;
  private final List<BookingRoomRequestedItem> rooms;

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
