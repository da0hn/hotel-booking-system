package com.hotel.booking.system.commons.core.domain.event.customer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.hotel.booking.system.commons.core.domain.event.BookingRoomItemRepresentation;
import com.hotel.booking.system.commons.core.domain.valueobject.CustomerReservationStatus;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@SuperBuilder
public final class CustomerBookingInitiatedEvent extends CustomerBookingStatusUpdatedEvent {

  private final String hotelId;
  private final BigDecimal totalPrice;
  private final Integer guests;
  private final LocalDate checkIn;
  private final LocalDate checkOut;
  private final List<BookingRoomItemRepresentation> rooms;

  public CustomerBookingInitiatedEvent(
    final CustomerBookingStatusUpdatedEventBuilder<?, ?> builder,
    final String hotelId,
    final BigDecimal totalPrice,
    final Integer guests,
    final LocalDate checkIn,
    final LocalDate checkOut,
    final List<BookingRoomItemRepresentation> rooms
  ) {
    super(builder);
    this.hotelId = hotelId;
    this.totalPrice = totalPrice;
    this.guests = guests;
    this.checkIn = checkIn;
    this.checkOut = checkOut;
    this.rooms = rooms;
  }


  @JsonCreator
  public CustomerBookingInitiatedEvent(
    final String reservationOrderId,
    final String customerId,
    final CustomerReservationStatus status,
    final String hotelId,
    final BigDecimal totalPrice,
    final Integer guests,
    final LocalDate checkIn,
    final LocalDate checkOut,
    final List<BookingRoomItemRepresentation> rooms
  ) {
    super(reservationOrderId, customerId, status);
    this.hotelId = hotelId;
    this.totalPrice = totalPrice;
    this.guests = guests;
    this.checkIn = checkIn;
    this.checkOut = checkOut;
    this.rooms = rooms;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE, false, CustomerBookingStatusUpdatedEvent.class);
  }

}
