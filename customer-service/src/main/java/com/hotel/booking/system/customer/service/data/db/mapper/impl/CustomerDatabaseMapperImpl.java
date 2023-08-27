package com.hotel.booking.system.customer.service.data.db.mapper.impl;

import com.hotel.booking.system.customer.service.core.domain.entity.ReservationOrder;
import com.hotel.booking.system.customer.service.core.domain.entity.ReservationOrderTimeline;
import com.hotel.booking.system.customer.service.data.db.entity.ReservationOrderEntity;
import com.hotel.booking.system.customer.service.data.db.entity.ReservationOrderHistoryEntity;
import com.hotel.booking.system.customer.service.data.db.mapper.CustomerDatabaseMapper;
import com.hotel.booking.system.customer.service.data.db.repository.adapters.CustomerRepositoryAdapter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CustomerDatabaseMapperImpl implements CustomerDatabaseMapper {

  private final CustomerRepositoryAdapter repository;

  @Override
  public ReservationOrderEntity reservationOrderToReservationOrderEntity(final ReservationOrder reservationOrder) {
    final var entity = ReservationOrderEntity.builder()
      .id(reservationOrder.getId().getValue())
      .customer(this.repository.findCustomerEntityById(reservationOrder.getCustomerId()))
      .hotelId(reservationOrder.getHotelId().getValue())
      .guests(reservationOrder.getGuests())
      .checkIn(reservationOrder.getCheckIn())
      .checkOut(reservationOrder.getCheckOut())
      .totalPrice(reservationOrder.getTotalPrice().getValue())
      .currentStatus(reservationOrder.getCurrentStatus())
      .history(reservationOrder.getTimeline().stream()
        .map(this::reservationOrderHistoryToReservationOrderHistoryEntity)
        .collect(Collectors.toSet()))
      .build();
    entity.getHistory().forEach(history -> history.setReservationOrder(entity));
    return entity;
  }

  @Override
  public ReservationOrderHistoryEntity reservationOrderHistoryToReservationOrderHistoryEntity(final ReservationOrderTimeline entity) {
    return ReservationOrderHistoryEntity.builder()
      .id(entity.getId().getValue())
      .status(entity.getStatus())
      .occurredAt(entity.getOccurredAt())
      .build();
  }
}
