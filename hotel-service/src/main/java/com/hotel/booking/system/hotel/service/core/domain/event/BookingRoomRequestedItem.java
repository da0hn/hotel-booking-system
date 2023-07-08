package com.hotel.booking.system.hotel.service.core.domain.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Builder
@Getter
@AllArgsConstructor
public class BookingRoomRequestedItem {
  private final String roomId;
  private final Integer roomQuantity;

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
