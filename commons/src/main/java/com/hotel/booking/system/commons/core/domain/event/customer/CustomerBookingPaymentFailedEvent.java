package com.hotel.booking.system.commons.core.domain.event.customer;

import com.hotel.booking.system.commons.core.domain.valueobject.CustomerReservationStatus;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@SuperBuilder
public final class CustomerBookingPaymentFailedEvent extends CustomerBookingStatusUpdatedEvent {


  public CustomerBookingPaymentFailedEvent(
    final String reservationOrderId,
    final String customerId,
    final CustomerReservationStatus status
  ) {
    super(reservationOrderId, customerId, status);
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
