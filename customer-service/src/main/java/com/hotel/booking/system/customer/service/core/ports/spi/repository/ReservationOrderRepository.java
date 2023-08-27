package com.hotel.booking.system.customer.service.core.ports.spi.repository;

import com.hotel.booking.system.commons.core.domain.valueobject.ReservationOrderId;
import com.hotel.booking.system.customer.service.core.domain.entity.ReservationOrder;

public interface ReservationOrderRepository {
  void save(ReservationOrder reservationOrder);

  ReservationOrder findById(ReservationOrderId id);
}
