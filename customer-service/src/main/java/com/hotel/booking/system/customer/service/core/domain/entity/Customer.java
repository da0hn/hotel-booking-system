package com.hotel.booking.system.customer.service.core.domain.entity;

import com.hotel.booking.system.commons.core.domain.AbstractDomainEntity;
import com.hotel.booking.system.commons.core.domain.valueobject.CustomerId;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Customer extends AbstractDomainEntity<CustomerId> {

  private final String name;
  private final String cpf;

  protected Customer(final CustomerId id, final String name, final String cpf) {
    super(id);
    this.name = name;
    this.cpf = cpf;
  }


}
