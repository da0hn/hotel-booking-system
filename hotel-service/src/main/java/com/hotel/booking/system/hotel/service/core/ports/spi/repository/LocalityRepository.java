package com.hotel.booking.system.hotel.service.core.ports.spi.repository;

import com.hotel.booking.system.hotel.service.core.domain.valueobject.LocalityId;

public interface LocalityRepository {
  boolean existsLocalityById(LocalityId localityId);
}
