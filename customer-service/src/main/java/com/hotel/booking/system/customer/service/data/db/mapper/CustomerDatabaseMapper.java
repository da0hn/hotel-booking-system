package com.hotel.booking.system.customer.service.data.db.mapper;

import com.hotel.booking.system.customer.service.core.domain.entity.Customer;
import com.hotel.booking.system.customer.service.core.domain.entity.ReservationOrder;
import com.hotel.booking.system.customer.service.core.domain.entity.ReservationOrderTimeline;
import com.hotel.booking.system.customer.service.data.db.entity.CustomerEntity;
import com.hotel.booking.system.customer.service.data.db.entity.ReservationOrderEntity;
import com.hotel.booking.system.customer.service.data.db.entity.ReservationOrderHistoryEntity;

public interface CustomerDatabaseMapper {
  ReservationOrderEntity reservationOrderToReservationOrderEntity(ReservationOrder reservationOrder, CustomerEntity customerEntity);

  ReservationOrderHistoryEntity reservationOrderHistoryToReservationOrderHistoryEntity(ReservationOrderTimeline reservationOrderTimeline);

  ReservationOrder reservationOrderEntityToReservationOrder(ReservationOrderEntity entity);

  ReservationOrderTimeline reservationOrderHistoryEntityToReservationOrderTimeline(ReservationOrderHistoryEntity entity);

  Customer customerEntityToCustomer(CustomerEntity customerEntity);
}
