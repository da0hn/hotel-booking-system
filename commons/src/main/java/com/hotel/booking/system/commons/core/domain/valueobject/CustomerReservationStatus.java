package com.hotel.booking.system.commons.core.domain.valueobject;

public enum CustomerReservationStatus {

  AWAITING_RESERVATION,
  AWAITING_PAYMENT,
  PAYMENT_CONFIRMED,
  PAYMENT_FAILED,
  RESERVED,
  RESERVATION_FAILED;

  public static boolean isFailureStatus(final CustomerReservationStatus status) {
    if (status == null) return false;
    return CustomerReservationStatus.PAYMENT_FAILED == status || CustomerReservationStatus.RESERVATION_FAILED == status;
  }

}
