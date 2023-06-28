package com.hotel.booking.system.hotel.service.core.domain.valueobject;

import java.math.BigDecimal;

public class Money {

  private final BigDecimal value;

  private Money(final BigDecimal value) {this.value = value;}

  public static Money of(final BigDecimal value) {
    return new Money(value);
  }

  public static Money of(final Integer value) {
    return new Money(BigDecimal.valueOf(value));
  }

  public static Money of(final Double value) {
    return new Money(BigDecimal.valueOf(value));
  }

  public BigDecimal getValue() {
    return this.value;
  }
}
