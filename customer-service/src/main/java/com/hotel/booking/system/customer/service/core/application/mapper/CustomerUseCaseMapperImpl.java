package com.hotel.booking.system.customer.service.core.application.mapper;

import com.hotel.booking.system.commons.core.domain.event.customer.CustomerBookingInitiatedEvent;
import com.hotel.booking.system.commons.core.domain.valueobject.CustomerId;
import com.hotel.booking.system.commons.core.domain.valueobject.HotelId;
import com.hotel.booking.system.commons.core.domain.valueobject.Money;
import com.hotel.booking.system.commons.core.domain.valueobject.ReservationOrderId;
import com.hotel.booking.system.customer.service.core.application.dto.InitializeReservationOrderInput;
import com.hotel.booking.system.customer.service.core.domain.entity.ReservationOrder;
import com.hotel.booking.system.customer.service.core.ports.api.mapper.CustomerUseCaseMapper;

public class CustomerUseCaseMapperImpl implements CustomerUseCaseMapper {
  @Override
  public InitializeReservationOrderInput customerBookingInitiatedEventToInitializeCustomerBookingInput(final CustomerBookingInitiatedEvent event) {
    return new InitializeReservationOrderInput(
      event.getReservationOrderId(),
      event.getCustomerId(),
      event.getHotelId(),
      event.getTotalPrice(),
      event.getGuests(),
      event.getCheckIn(),
      event.getCheckOut(),
      event.getStatus()
    );
  }

  @Override
  public ReservationOrder initializeReservationOrderInputToReservationOrder(final InitializeReservationOrderInput input) {
    return ReservationOrder.builder()
      .id(ReservationOrderId.of(input.reservationOrderId()))
      .customerId(CustomerId.of(input.customerId()))
      .hotelId(HotelId.of(input.hotelId()))
      .totalPrice(Money.of(input.totalPrice()))
      .guests(input.guests())
      .checkIn(input.checkIn())
      .checkOut(input.checkOut())
      .build();
  }
}
