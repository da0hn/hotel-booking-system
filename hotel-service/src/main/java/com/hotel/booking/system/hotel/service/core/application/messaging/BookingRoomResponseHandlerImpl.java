package com.hotel.booking.system.hotel.service.core.application.messaging;

import com.hotel.booking.system.commons.core.domain.event.BookingRoomFailedEvent;
import com.hotel.booking.system.commons.core.domain.event.BookingRoomPaymentRequestedEvent;
import com.hotel.booking.system.commons.core.domain.event.BookingRoomPendingEvent;
import com.hotel.booking.system.commons.core.domain.event.BookingRoomRejectedEvent;
import com.hotel.booking.system.commons.core.domain.event.BookingRoomResponseEvent;
import com.hotel.booking.system.commons.core.domain.event.BookingRoomStatusUpdatedEvent;
import com.hotel.booking.system.commons.core.domain.event.PaymentRequestedEvent;
import com.hotel.booking.system.commons.core.domain.valueobject.CustomerReservationStatus;
import com.hotel.booking.system.hotel.service.core.ports.api.messaging.BookingRoomResponseHandler;
import com.hotel.booking.system.hotel.service.core.ports.spi.messaging.publisher.CustomerBookingRoomStatusUpdatedPublisher;
import com.hotel.booking.system.hotel.service.core.ports.spi.messaging.publisher.PaymentRequestedPublisher;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BookingRoomResponseHandlerImpl implements BookingRoomResponseHandler {

  private final CustomerBookingRoomStatusUpdatedPublisher customerBookingRoomUpdatedPublisher;
  private final PaymentRequestedPublisher paymentRequestedPublisher;

  public BookingRoomResponseHandlerImpl(
    final CustomerBookingRoomStatusUpdatedPublisher customerBookingRoomUpdatedPublisher,
    final PaymentRequestedPublisher paymentRequestedPublisher
  ) {
    this.customerBookingRoomUpdatedPublisher = customerBookingRoomUpdatedPublisher;
    this.paymentRequestedPublisher = paymentRequestedPublisher;
  }

  @Override
  public void handle(final BookingRoomResponseEvent event) {
    switch (event) {
      case final BookingRoomPendingEvent e -> {
        log.info("Booking room pending, notifying customer service | reservationOrderId={}", e.getReservationOrderId());
        final var bookingRoomStatusUpdatedEvent = this.bookingRoomPendingEventToBookingRoomPaymentRequested(e);
        this.customerBookingRoomUpdatedPublisher.publish(bookingRoomStatusUpdatedEvent);
        log.info("Customer service notified | reservationOrderId={}", e.getReservationOrderId());
        log.info("Booking room pending, requesting for payment | reservationOrderId={}", e.getReservationOrderId());
        final var paymentRequestedEvent = this.bookingRoomPendingEventToPaymentRequestedEvent(e);
        this.paymentRequestedPublisher.publish(paymentRequestedEvent);
        log.info("Payment service notified | reservationOrderId={}", e.getReservationOrderId());
      }
      case final BookingRoomFailedEvent e -> {
        log.info(
          "Booking room failed with error messages \"{}\". Notifying customer service | reservationOrderId={}",
          String.join(", ", e.getFailureMessages()),
          e.getReservationOrderId()
        );
        final var bookingRoomStatusUpdatedEvent = this.bookingRoomFailedEventToBookingRoomRejectedEvent(e);
        this.customerBookingRoomUpdatedPublisher.publish(bookingRoomStatusUpdatedEvent);
        log.info("Customer service notified | reservationOrderId={}", e.getReservationOrderId());
      }
      default -> throw new IllegalStateException(
        "Failed on handling sub-type of BookingRoomResponseEvent: Unknown event"
      );
    }
  }

  private PaymentRequestedEvent bookingRoomPendingEventToPaymentRequestedEvent(final BookingRoomPendingEvent event) {
    return PaymentRequestedEvent.builder()
      .reservationOrderId(event.getReservationOrderId())
      .bookingRoomId(event.getBookingRoomId())
      .customerId(event.getCustomerId())
      .totalPrice(event.getTotalPrice())
      .build();
  }

  private BookingRoomStatusUpdatedEvent bookingRoomPendingEventToBookingRoomPaymentRequested(final BookingRoomPendingEvent event) {
    return BookingRoomPaymentRequestedEvent.builder()
      .bookingRoomId(event.getBookingRoomId())
      .reservationOrderId(event.getReservationOrderId())
      .customerId(event.getCustomerId())
      .status(CustomerReservationStatus.AWAITING_PAYMENT)
      .build();
  }

  private BookingRoomStatusUpdatedEvent bookingRoomFailedEventToBookingRoomRejectedEvent(final BookingRoomFailedEvent event) {
    return BookingRoomRejectedEvent.builder()
      .reservationOrderId(event.getReservationOrderId())
      .failureMessages(event.getFailureMessages())
      .customerId(event.getCustomerId())
      .status(CustomerReservationStatus.RESERVATION_FAILED)
      .build();
  }
}
