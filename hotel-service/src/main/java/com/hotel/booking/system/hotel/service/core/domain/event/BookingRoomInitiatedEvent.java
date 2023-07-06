package com.hotel.booking.system.hotel.service.core.domain.event;

import com.hotel.booking.system.commons.core.domain.event.Event;
import com.hotel.booking.system.hotel.service.core.domain.valueobject.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class BookingRoomInitiatedEvent implements Event {

  @Builder.Default
  private final Instant createdAt = Instant.now();
  private final String reservationOrderId;
  private final String customerId;
  private final BigDecimal totalPrice;
  private final Integer guests;
  private final LocalDate checkIn;
  private final LocalDate checkOut;
  private final ReservationStatus status;
  private final List<BookingRoomInitiatedItem> rooms;

}
