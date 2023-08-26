package com.hotel.booking.system.booking.service.core.application.messaging;

import com.hotel.booking.system.booking.service.core.ports.api.mapper.BookingUseCaseMapper;
import com.hotel.booking.system.booking.service.core.ports.api.messaging.BookingRoomStatusChangedHandler;
import com.hotel.booking.system.booking.service.core.ports.api.usecase.UpdateBookingStatusUseCase;
import com.hotel.booking.system.commons.core.domain.event.BookingRoomStatusUpdatedEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BookingRoomStatusChangedHandlerImpl implements BookingRoomStatusChangedHandler {

  private final BookingUseCaseMapper bookingUseCaseMapper;
  private final UpdateBookingStatusUseCase updateBookingRoomStatusUseCase;

  public BookingRoomStatusChangedHandlerImpl(
    final BookingUseCaseMapper bookingUseCaseMapper,
    final UpdateBookingStatusUseCase updateBookingRoomStatusUseCase
  ) {
    this.bookingUseCaseMapper = bookingUseCaseMapper;
    this.updateBookingRoomStatusUseCase = updateBookingRoomStatusUseCase;
  }

  @Override
  public void handle(final BookingRoomStatusUpdatedEvent event) {
    log.info("A Booking Room update received | reservationOrderId={}, event={}", event.getReservationOrderId(), event);
    final var input = this.bookingUseCaseMapper.bookingRoomStatusUpdatedEventToUpdateBookingRoomStatusInput(event);
    this.updateBookingRoomStatusUseCase.execute(input);
    log.info("A Booking Room update received | reservationOrderId={}, event={}", event.getReservationOrderId(), event);
  }
}
