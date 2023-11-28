package com.hotel.booking.system.customer.service.core.application.mapper;

import com.hotel.booking.system.commons.core.domain.event.customer.CustomerBookingFailureStatusUpdateEvent;
import com.hotel.booking.system.commons.core.domain.event.customer.CustomerBookingInitiatedEvent;
import com.hotel.booking.system.commons.core.domain.event.customer.CustomerBookingStatusUpdatedEvent;
import com.hotel.booking.system.commons.core.domain.valueobject.CustomerId;
import com.hotel.booking.system.commons.core.domain.valueobject.FailureMessages;
import com.hotel.booking.system.commons.core.domain.valueobject.HotelId;
import com.hotel.booking.system.commons.core.domain.valueobject.Money;
import com.hotel.booking.system.commons.core.domain.valueobject.ReservationOrderId;
import com.hotel.booking.system.customer.service.core.application.dto.InitializeReservationOrderInput;
import com.hotel.booking.system.customer.service.core.application.dto.ReservationOrderDetailOutput;
import com.hotel.booking.system.customer.service.core.application.dto.TimelineItem;
import com.hotel.booking.system.customer.service.core.application.dto.UpdateCustomerBookingFailureStatusInput;
import com.hotel.booking.system.customer.service.core.application.dto.UpdateCustomerBookingStatusInput;
import com.hotel.booking.system.customer.service.core.domain.entity.Customer;
import com.hotel.booking.system.customer.service.core.domain.entity.ReservationOrder;
import com.hotel.booking.system.customer.service.core.domain.entity.ReservationOrderTimeline;
import com.hotel.booking.system.customer.service.core.ports.api.mapper.CustomerUseCaseMapper;

import java.time.ZoneId;

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

  @Override
  public UpdateCustomerBookingStatusInput customerBookingStatusUpdateEventToUpdateCustomerBookingStatusInput(
    final CustomerBookingStatusUpdatedEvent event
  ) {
    return new UpdateCustomerBookingStatusInput(
      event.getCustomerId(),
      event.getReservationOrderId(),
      event.getStatus()
    );
  }

  @Override
  public ReservationOrderDetailOutput reservationOrderToReservationOrderDetailOutput(
    final ReservationOrder reservationOrder,
    final Customer customer
  ) {
    return ReservationOrderDetailOutput.builder()
      .customerId(customer.getId().toString())
      .customerCpf(customer.getCpf().formatted())
      .customerName(customer.getName())
      .reservationOrderId(reservationOrder.getId().toString())
      .hotelId(reservationOrder.getHotelId().toString())
      .totalPrice(reservationOrder.getTotalPrice().getValue())
      .checkIn(reservationOrder.getCheckIn())
      .checkOut(reservationOrder.getCheckOut())
      .status(reservationOrder.getCurrentStatus())
      .timeline(reservationOrder.getTimeline().mapToListOf(this::reservationOrderTimelineToTimelineItem))
      .build();
  }

  @Override
  public UpdateCustomerBookingFailureStatusInput customerBookingFailureStatusUpdateEventToUpdateCustomerBookingFailureStatusInput(
    final CustomerBookingFailureStatusUpdateEvent event
  ) {
    return new UpdateCustomerBookingFailureStatusInput(
      event.getCustomerId(),
      event.getReservationOrderId(),
      event.getStatus(),
      FailureMessages.newInstance(event.getFailureMessages())
    );
  }

  private TimelineItem reservationOrderTimelineToTimelineItem(final ReservationOrderTimeline entity) {
    return new TimelineItem(
      entity.getStatus(),
      entity.getOccurredAt()
        .atZone(ZoneId.of("GMT-4"))
        .toLocalDateTime(),
      entity.getReason()
    );
  }
}
