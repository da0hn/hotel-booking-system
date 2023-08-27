package com.hotel.booking.system.commons.core.domain.event.customer;

import com.hotel.booking.system.commons.core.domain.valueobject.CustomerReservationStatus;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@SuperBuilder
public final class CustomerBookingPaymentRequestedEvent extends CustomerBookingStatusUpdatedEvent {

  private final String bookingRoomId;

  public CustomerBookingPaymentRequestedEvent(
    final String reservationOrderId,
    final String customerId,
    final CustomerReservationStatus status,
    final String bookingRoomId
  ) {
    super(reservationOrderId, customerId, status);
    this.bookingRoomId = bookingRoomId;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
