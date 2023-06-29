package com.hotel.booking.system.hotel.service.data.db.repository.adapters;

import com.hotel.booking.system.hotel.service.core.domain.valueobject.LocalityId;
import com.hotel.booking.system.hotel.service.core.ports.spi.LocalityRepository;
import com.hotel.booking.system.hotel.service.data.db.repository.LocalityJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LocalityRepositoryAdapter implements LocalityRepository {

  private final LocalityJpaRepository localityJpaRepository;

  @Override
  public boolean existsLocalityById(final LocalityId localityId) {
    return this.localityJpaRepository.existsById(localityId.getValue());
  }
}
