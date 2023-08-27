package com.hotel.booking.system.customer.service.data.db.repository.adapters;

import com.hotel.booking.system.commons.core.domain.valueobject.ReservationOrderId;
import com.hotel.booking.system.customer.service.core.domain.entity.ReservationOrder;
import com.hotel.booking.system.customer.service.core.domain.exception.ReservationOrderNotFoundException;
import com.hotel.booking.system.customer.service.core.ports.spi.repository.ReservationOrderRepository;
import com.hotel.booking.system.customer.service.data.db.mapper.CustomerDatabaseMapper;
import com.hotel.booking.system.customer.service.data.db.repository.ReservationOrderJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReservationOrderRepositoryAdapter implements ReservationOrderRepository {

  private final ReservationOrderJpaRepository repository;
  private final CustomerDatabaseMapper mapper;


  @Override
  public void save(final ReservationOrder reservationOrder) {
    final var entity = this.mapper.reservationOrderToReservationOrderEntity(reservationOrder);
    this.repository.save(entity);
  }

  @Override
  public ReservationOrder findById(final ReservationOrderId reservationOrderId) {
    return this.repository.findById(reservationOrderId.getValue())
      .map(this.mapper::reservationOrderEntityToReservationOrder)
      .orElseThrow(ReservationOrderNotFoundException::new);
  }
}
