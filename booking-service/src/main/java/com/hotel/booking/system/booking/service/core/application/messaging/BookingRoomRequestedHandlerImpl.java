package com.hotel.booking.system.booking.service.core.application.messaging;

import com.hotel.booking.system.booking.service.core.application.dto.BookingRoomOutput;
import com.hotel.booking.system.booking.service.core.ports.api.mapper.BookingUseCaseMapper;
import com.hotel.booking.system.booking.service.core.ports.api.messaging.BookingRoomRequestedHandler;
import com.hotel.booking.system.booking.service.core.ports.api.usecase.BookingRoomUseCase;
import com.hotel.booking.system.booking.service.core.ports.spi.messaging.publisher.BookingRoomResponsePublisher;
import com.hotel.booking.system.commons.core.domain.event.BookingRoomRequestedEvent;
import com.hotel.booking.system.commons.core.domain.event.BookingRoomResponseEvent;

public class BookingRoomRequestedHandlerImpl implements BookingRoomRequestedHandler {

  private final BookingRoomUseCase bookingRoomUseCase;
  private final BookingUseCaseMapper mapper;
  private final BookingRoomResponsePublisher publisher;

  public BookingRoomRequestedHandlerImpl(
    final BookingRoomUseCase bookingRoomUseCase,
    final BookingUseCaseMapper mapper,
    final BookingRoomResponsePublisher publisher
  ) {
    this.bookingRoomUseCase = bookingRoomUseCase;
    this.mapper = mapper;
    this.publisher = publisher;
  }

  @Override
  public void handle(final BookingRoomRequestedEvent event) {
    final var input = this.mapper.bookingRoomRequestedEventToBookingRoomInput(event);

    final var output = this.bookingRoomUseCase.execute(input);

    final var bookingRoomResponseEvent = this.mapOutputToEvent(output);

    this.publisher.publish(bookingRoomResponseEvent);
  }

  private BookingRoomResponseEvent mapOutputToEvent(final BookingRoomOutput output) {
    if (output.failureMessages().isNotEmpty()) {
      return this.mapper.bookingRoomOutputToBookingRoomFailedEvent(output);
    } else {
      return this.mapper.bookingRoomOutputToBookingRoomResponseEvent(output);
    }
  }
}
