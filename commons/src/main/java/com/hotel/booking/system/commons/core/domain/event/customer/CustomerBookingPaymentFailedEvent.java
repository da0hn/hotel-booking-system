package com.hotel.booking.system.commons.core.domain.event.customer;

import com.hotel.booking.system.commons.core.domain.valueobject.CustomerReservationStatus;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

@Getter
@SuperBuilder
public final class CustomerBookingPaymentFailedEvent extends CustomerBookingFailureStatusUpdateEvent {


  public CustomerBookingPaymentFailedEvent(
    final String reservationOrderId,
    final String customerId,
    final CustomerReservationStatus status,
    final List<String> failureMessages
  ) {
    super(reservationOrderId, customerId, status, failureMessages);
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
