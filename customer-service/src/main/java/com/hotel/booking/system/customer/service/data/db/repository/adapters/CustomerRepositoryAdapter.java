package com.hotel.booking.system.customer.service.data.db.repository.adapters;

import com.hotel.booking.system.commons.core.domain.valueobject.CustomerId;
import com.hotel.booking.system.customer.service.core.domain.exception.CustomerNotFoundException;
import com.hotel.booking.system.customer.service.core.ports.spi.repository.CustomerRepository;
import com.hotel.booking.system.customer.service.data.db.entity.CustomerEntity;
import com.hotel.booking.system.customer.service.data.db.repository.CustomerJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomerRepositoryAdapter implements CustomerRepository {

  private final CustomerJpaRepository repository;

  @Override
  public boolean customerExistsBy(final CustomerId customerId) {
    return this.repository.existsById(customerId.getValue());
  }

  public CustomerEntity findCustomerEntityById(final CustomerId customerId) {
    return this.repository.findById(customerId.getValue())
      .orElseThrow(CustomerNotFoundException::new);
  }

}
