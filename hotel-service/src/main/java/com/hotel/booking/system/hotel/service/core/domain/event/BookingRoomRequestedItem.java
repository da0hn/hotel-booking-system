package com.hotel.booking.system.hotel.service.core.domain.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class BookingRoomRequestedItem {
  private final String roomId;
  private final Integer roomQuantity;
}
