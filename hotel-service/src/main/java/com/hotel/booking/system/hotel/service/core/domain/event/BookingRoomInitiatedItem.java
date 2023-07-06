package com.hotel.booking.system.hotel.service.core.domain.event;

import com.hotel.booking.system.commons.core.domain.event.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
public class BookingRoomInitiatedItem implements Event {

  private final String roomId;
  private final BigDecimal price;
  private final Integer quantity;

}
