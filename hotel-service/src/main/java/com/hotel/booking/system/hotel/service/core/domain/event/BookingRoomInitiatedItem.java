package com.hotel.booking.system.hotel.service.core.domain.event;

import com.hotel.booking.system.commons.core.domain.event.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
public class BookingRoomInitiatedItem implements Event {

  private final String roomId;
  private final BigDecimal price;
  private final Integer quantity;

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
