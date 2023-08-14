package com.hotel.booking.system.commons.core.domain.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

@Builder
@Getter
@AllArgsConstructor
public class BookingRoomItemRepresentation implements Event {

  private final String roomId;
  private final BigDecimal price;
  private final Integer quantity;

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
