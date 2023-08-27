package com.hotel.booking.system.booking.service.core.application.messaging;

import com.hotel.booking.system.booking.service.core.application.dto.UpdateBookingStatusOutput;
import com.hotel.booking.system.booking.service.core.ports.api.mapper.BookingUseCaseMapper;
import com.hotel.booking.system.booking.service.core.ports.api.messaging.BookingRoomStatusChangedHandler;
import com.hotel.booking.system.booking.service.core.ports.api.usecase.UpdateBookingStatusUseCase;
import com.hotel.booking.system.booking.service.core.ports.spi.messaging.publisher.BookingRoomResponsePublisher;
import com.hotel.booking.system.commons.core.domain.event.BookingRoomConfirmedEvent;
import com.hotel.booking.system.commons.core.domain.event.BookingRoomPaymentCompleted;
import com.hotel.booking.system.commons.core.domain.event.BookingRoomResponseEvent;
import com.hotel.booking.system.commons.core.domain.event.BookingRoomStatusUpdatedEvent;
import com.hotel.booking.system.commons.core.domain.valueobject.CustomerReservationStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BookingRoomStatusChangedHandlerImpl implements BookingRoomStatusChangedHandler {

  private final BookingUseCaseMapper bookingUseCaseMapper;
  private final UpdateBookingStatusUseCase updateBookingRoomStatusUseCase;
  private final BookingRoomResponsePublisher bookingRoomResponsePublisher;

  public BookingRoomStatusChangedHandlerImpl(
    final BookingUseCaseMapper bookingUseCaseMapper,
    final UpdateBookingStatusUseCase updateBookingRoomStatusUseCase,
    final BookingRoomResponsePublisher bookingRoomResponsePublisher
  ) {
    this.bookingUseCaseMapper = bookingUseCaseMapper;
    this.updateBookingRoomStatusUseCase = updateBookingRoomStatusUseCase;
    this.bookingRoomResponsePublisher = bookingRoomResponsePublisher;
  }

  @Override
  public void handle(final BookingRoomStatusUpdatedEvent event) {
    log.info("A Booking Room update received | reservationOrderId={}, event={}", event.getReservationOrderId(), event);
    final var input = this.bookingUseCaseMapper.bookingRoomStatusUpdatedEventToUpdateBookingRoomStatusInput(event);
    final var output = this.updateBookingRoomStatusUseCase.execute(input);
    log.info("A Booking Room update received | reservationOrderId={}, event={}", event.getReservationOrderId(), event);
    if (event instanceof BookingRoomPaymentCompleted) {
      log.info(
        "Payment completed, sending Booking confirmation to Hotel Service | reservationOrderId={}",
        event.getReservationOrderId()
      );
      this.bookingRoomResponsePublisher.publish(this.bookingRoomStatusOutputToBookingRoomConfirmedEvent(output));
      log.info(
        "Hotel service notified | reservationOrderId={}",
        event.getReservationOrderId()
      );
    }
  }

  private BookingRoomResponseEvent bookingRoomStatusOutputToBookingRoomConfirmedEvent(final UpdateBookingStatusOutput output) {
    return new BookingRoomConfirmedEvent(
      output.reservationOrderId(),
      output.customerId(),
      CustomerReservationStatus.RESERVED
    );
  }
}
