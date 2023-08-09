package com.hotel.booking.system.booking.service.core.application.messaging;

import com.hotel.booking.system.booking.service.core.application.dto.BookingRoomOutput;
import com.hotel.booking.system.booking.service.core.ports.api.mapper.BookingUseCaseMapper;
import com.hotel.booking.system.booking.service.core.ports.api.messaging.BookingRoomRequestedHandler;
import com.hotel.booking.system.booking.service.core.ports.api.usecase.BookingRoomUseCase;
import com.hotel.booking.system.commons.core.domain.event.BookingRoomRequestedEvent;
import com.hotel.booking.system.commons.core.domain.event.BookingRoomResponseEvent;

public class BookingRoomRequestedHandlerImpl implements BookingRoomRequestedHandler {

  private final BookingRoomUseCase bookingRoomUseCase;
  private final BookingUseCaseMapper mapper;

  public BookingRoomRequestedHandlerImpl(
    final BookingRoomUseCase bookingRoomUseCase,
    final BookingUseCaseMapper mapper
  ) {
    this.bookingRoomUseCase = bookingRoomUseCase;
    this.mapper = mapper;
  }

  @Override
  public void handle(final BookingRoomRequestedEvent event) {
    final var input = this.mapper.bookingRoomRequestedEventToBookingRoomInput(event);

    final var output = this.bookingRoomUseCase.execute(input);

    final var bookingRoomResponseEvent = this.mapOutputToEvent(output);
    // TODO: enviar evento para o microsserviço `hotel-service`
  }

  private BookingRoomResponseEvent mapOutputToEvent(final BookingRoomOutput output) {
    if (output.failureMessages().isNotEmpty()) {
      return this.mapper.bookingRoomOutputToBookingRoomFailedEvent(output);
    } else {
      return this.mapper.bookingRoomOutputToBookingRoomResponseEvent(output);
    }
  }
}
