package com.hotel.booking.system.customer.service.core.ports.spi.repository;

import com.hotel.booking.system.commons.core.domain.valueobject.CustomerId;

public interface CustomerRepository {
  boolean customerExistsBy(CustomerId customerId);
}
