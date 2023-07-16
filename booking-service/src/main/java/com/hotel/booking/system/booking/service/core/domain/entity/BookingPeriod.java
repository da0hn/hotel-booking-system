package com.hotel.booking.system.booking.service.core.domain.entity;

import com.hotel.booking.system.booking.service.core.domain.exception.BookingDomainException;
import com.hotel.booking.system.commons.core.message.ApplicationMessage;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDate;
import java.util.Objects;

public class BookingPeriod {

  private final LocalDate checkIn;
  private final LocalDate checkOut;

  private BookingPeriod(
    final LocalDate checkIn,
    final LocalDate checkOut
  ) {
    this.checkIn = checkIn;
    this.checkOut = checkOut;
    this.validate();
  }

  public static BookingPeriod of(final LocalDate checkIn, final LocalDate checkOut) {
    Objects.requireNonNull(
      checkIn,
      ApplicationMessage.BOOKING_PERIOD_CHECK_IN_NOT_NULL
    );
    Objects.requireNonNull(
      checkOut,
      ApplicationMessage.BOOKING_PERIOD_CHECK_OUT_NOT_NULL
    );
    return new BookingPeriod(checkIn, checkOut);
  }

  public void validate() {
    if (this.checkIn.isAfter(this.checkOut)) {
      throw new BookingDomainException(ApplicationMessage.BOOKING_PERIOD_CHECK_IN_AFTER_CHECK_OUT);
    }
  }

  public boolean isContainedIn(final BookingPeriod other) {
    final var containsOtherPeriod = this.checkIn.isBefore(other.checkIn) && this.checkOut.isAfter(other.checkOut);
    final var isTotallyContained = this.checkIn.isAfter(other.checkIn) && this.checkOut.isBefore(other.checkOut);
    final var isEqualCheckOut = this.checkOut.isEqual(other.checkOut);
    final var isEqualCheckIn = this.checkIn.isEqual(other.checkIn);
    final var isPartiallyContainedByCheckIn = this.checkIn.isAfter(other.checkIn) && this.checkIn.isBefore(other.checkOut);
    final var isPartiallyContainedByCheckOut = this.checkOut.isAfter(other.checkIn) && this.checkOut.isBefore(other.checkOut);
    return containsOtherPeriod
      || isTotallyContained
      || isPartiallyContainedByCheckIn
      || isPartiallyContainedByCheckOut
      || isEqualCheckIn
      || isEqualCheckOut;
  }


  public LocalDate getCheckIn() {
    return this.checkIn;
  }

  public LocalDate getCheckOut() {
    return this.checkOut;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || this.getClass() != o.getClass()) return false;

    final BookingPeriod that = (BookingPeriod) o;

    if (!this.checkIn.equals(that.checkIn)) return false;
    return this.checkOut.equals(that.checkOut);
  }

  @Override
  public int hashCode() {
    int result = this.checkIn.hashCode();
    result = 31 * result + this.checkOut.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
