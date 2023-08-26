package com.hotel.booking.system.commons.core.domain.event;

import com.hotel.booking.system.commons.core.domain.valueobject.BookingStatus;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@SuperBuilder
public final class BookingRoomPaymentCompleted extends BookingRoomStatusUpdatedEvent {

  public BookingRoomPaymentCompleted(
    final String reservationOrderId,
    final String customerId,
    final BookingStatus status
  ) {
    super(reservationOrderId, customerId, status);
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
