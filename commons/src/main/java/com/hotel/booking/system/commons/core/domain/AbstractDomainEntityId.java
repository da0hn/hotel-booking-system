package com.hotel.booking.system.commons.core.domain;

import java.util.Objects;
import java.util.StringJoiner;

public abstract class AbstractDomainEntityId<T> {

  protected final T value;

  protected AbstractDomainEntityId(final T value) {this.value = value;}

  public T getValue() {
    return this.value;
  }

  public boolean empty() {
    return Objects.isNull(this.value);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || this.getClass() != o.getClass()) return false;

    final AbstractDomainEntityId<?> that = (AbstractDomainEntityId<?>) o;

    return this.value.equals(that.value);
  }

  @Override
  public int hashCode() {
    return this.value.hashCode();
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", AbstractDomainEntityId.class.getSimpleName() + "[", "]")
      .add("value=" + this.value)
      .toString();
  }
}
