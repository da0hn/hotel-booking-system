package com.hotel.booking.system.hotel.service.core.domain.valueobject;

import java.math.BigDecimal;

public class Money {

  public static final Money ZERO = Money.of(BigDecimal.ZERO);
  private final BigDecimal value;

  private Money(final BigDecimal value) {
    this.value = value;
  }

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

  public boolean isNegative() {
    return this.value.compareTo(BigDecimal.ZERO) < 0;
  }

  public boolean isZero() {
    return this.value.compareTo(BigDecimal.ZERO) == 0;
  }

  public Money multiply(final BigDecimal value) {
    return new Money(this.value.multiply(value));
  }

  public Money add(final Money money) {
    return new Money(this.value.add(money.value));
  }
}
