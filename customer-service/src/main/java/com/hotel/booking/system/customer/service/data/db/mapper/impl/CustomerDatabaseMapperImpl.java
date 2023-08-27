package com.hotel.booking.system.customer.service.data.db.mapper.impl;

import com.hotel.booking.system.commons.core.domain.valueobject.CustomerId;
import com.hotel.booking.system.commons.core.domain.valueobject.HotelId;
import com.hotel.booking.system.commons.core.domain.valueobject.Money;
import com.hotel.booking.system.commons.core.domain.valueobject.ReservationOrderId;
import com.hotel.booking.system.customer.service.core.domain.entity.ReservationOrder;
import com.hotel.booking.system.customer.service.core.domain.entity.ReservationOrderTimeline;
import com.hotel.booking.system.customer.service.core.domain.valueobject.ReservationOrderTimelineId;
import com.hotel.booking.system.customer.service.core.domain.valueobject.Timeline;
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
  public ReservationOrderHistoryEntity reservationOrderHistoryToReservationOrderHistoryEntity(final ReservationOrderTimeline reservationOrderTimeline) {
    return ReservationOrderHistoryEntity.builder()
      .id(reservationOrderTimeline.getId().getValue())
      .status(reservationOrderTimeline.getStatus())
      .occurredAt(reservationOrderTimeline.getOccurredAt())
      .build();
  }

  @Override
  public ReservationOrder reservationOrderEntityToReservationOrder(final ReservationOrderEntity entity) {
    return ReservationOrder.builder()
      .id(ReservationOrderId.of(entity.getId()))
      .customerId(CustomerId.of(entity.getCustomer().getId()))
      .hotelId(HotelId.of(entity.getHotelId()))
      .checkIn(entity.getCheckIn())
      .checkOut(entity.getCheckOut())
      .guests(entity.getGuests())
      .totalPrice(Money.of(entity.getTotalPrice()))
      .currentStatus(entity.getCurrentStatus())
      .timeline(Timeline.of(
        entity.getHistory().stream()
          .map(this::reservationOrderHistoryEntityToReservationOrderTimeline)
          .collect(Collectors.toList())
      ))
      .build();
  }

  @Override
  public ReservationOrderTimeline reservationOrderHistoryEntityToReservationOrderTimeline(final ReservationOrderHistoryEntity entity) {
    return ReservationOrderTimeline.builder()
      .id(ReservationOrderTimelineId.of(entity.getId()))
      .status(entity.getStatus())
      .occurredAt(entity.getOccurredAt())
      .build();
  }
}
