package com.hotel.booking.system.commons.core.domain;

import lombok.experimental.SuperBuilder;

import java.util.StringJoiner;

@SuperBuilder
public abstract class AbstractDomainEntity<ID extends AbstractDomainEntityId<?>> {

  private ID id;

  protected AbstractDomainEntity(final ID id) {this.id = id;}

  public void setId(final ID id) {
    this.id = id;
  }

  public ID getId() {
    return this.id;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || this.getClass() != o.getClass()) return false;

    final AbstractDomainEntity<?> that = (AbstractDomainEntity<?>) o;

    return this.id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return this.id.hashCode();
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", AbstractDomainEntity.class.getSimpleName() + "[", "]")
      .add("id=" + this.id)
      .toString();
  }
}
