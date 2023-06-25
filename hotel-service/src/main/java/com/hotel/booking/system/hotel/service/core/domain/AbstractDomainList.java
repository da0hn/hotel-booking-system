package com.hotel.booking.system.hotel.service.core.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDomainList<T> implements DomainList<T> {

  private final List<T> data;

  protected AbstractDomainList(final List<T> data) {this.data = new ArrayList<>(data);}

  @Override
  public List<T> data() {
    return this.data;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || this.getClass() != o.getClass()) return false;

    final AbstractDomainList<?> that = (AbstractDomainList<?>) o;

    return this.data.equals(that.data);
  }

  @Override
  public int hashCode() {
    return this.data.hashCode();
  }
}
