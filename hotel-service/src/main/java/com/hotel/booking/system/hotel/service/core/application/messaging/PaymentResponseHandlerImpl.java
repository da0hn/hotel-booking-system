package com.hotel.booking.system.hotel.service.core.application.messaging;

import com.hotel.booking.system.commons.core.domain.event.BookingRoomPaymentCompletedEvent;
import com.hotel.booking.system.commons.core.domain.event.BookingRoomPaymentFailedEvent;
import com.hotel.booking.system.commons.core.domain.event.PaymentCompletedEvent;
import com.hotel.booking.system.commons.core.domain.event.PaymentFailedEvent;
import com.hotel.booking.system.commons.core.domain.event.PaymentResponseEvent;
import com.hotel.booking.system.commons.core.domain.valueobject.CustomerReservationStatus;
import com.hotel.booking.system.hotel.service.core.ports.api.messaging.PaymentResponseHandler;
import com.hotel.booking.system.hotel.service.core.ports.spi.messaging.publisher.BookingRoomStatusChangedPublisher;
import com.hotel.booking.system.hotel.service.core.ports.spi.messaging.publisher.CustomerBookingRoomStatusUpdatedPublisher;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PaymentResponseHandlerImpl implements PaymentResponseHandler {

  private final CustomerBookingRoomStatusUpdatedPublisher customerBookingRoomStatusUpdatedPublisher;
  private final BookingRoomStatusChangedPublisher bookingRoomStatusChangedPublisher;

  public PaymentResponseHandlerImpl(
    final CustomerBookingRoomStatusUpdatedPublisher customerBookingRoomStatusUpdatedPublisher,
    final BookingRoomStatusChangedPublisher bookingRoomStatusChangedPublisher
  ) {
    this.customerBookingRoomStatusUpdatedPublisher = customerBookingRoomStatusUpdatedPublisher;
    this.bookingRoomStatusChangedPublisher = bookingRoomStatusChangedPublisher;
  }

  @Override
  public void handle(final PaymentResponseEvent event) {
    switch (event) {
      case final PaymentCompletedEvent e -> {
        log.info(
          "Payment completed, notifying customer service & booking service | reservationOrderId={}",
          e.getReservationOrderId()
        );
        final var bookingRoomStatusUpdatedEvent = this.paymentCompletedEventToBookingRoomStatusUpdatedEvent(e);
        this.customerBookingRoomStatusUpdatedPublisher.publish(bookingRoomStatusUpdatedEvent);
        log.info("Customer service notified | reservationOrderId={}", e.getReservationOrderId());

        this.bookingRoomStatusChangedPublisher.publish(bookingRoomStatusUpdatedEvent);
        log.info("Booking service notified | reservationOrderId={}", e.getReservationOrderId());
      }
      case final PaymentFailedEvent e -> {
        log.info(
          "Payment failed with error messages \"{}\". Notifying customer service & booking service | reservationOrderId={}",
          String.join(", ", e.getFailureMessages()),
          e.getReservationOrderId()
        );

        final var bookingRoomPaymentFailedEvent = this.paymentFailedEventToBookingRoomPaymentFailedEvent(e);
        this.customerBookingRoomStatusUpdatedPublisher.publish(bookingRoomPaymentFailedEvent);
        log.info("Customer service notified | reservationOrderId={}", e.getReservationOrderId());

        this.bookingRoomStatusChangedPublisher.publish(bookingRoomPaymentFailedEvent);
        log.info("Booking service notified | reservationOrderId={}", e.getReservationOrderId());
      }
      default -> throw new IllegalStateException(
        "Failed on handling sub-type of PaymentResponseEvent: Unknown event"
      );
    }
  }

  private BookingRoomPaymentFailedEvent paymentFailedEventToBookingRoomPaymentFailedEvent(final PaymentFailedEvent event) {
    return BookingRoomPaymentFailedEvent.builder()
      .reservationOrderId(event.getReservationOrderId())
      .status(CustomerReservationStatus.PAYMENT_FAILED)
      .customerId(event.getCustomerId())
      .build();
  }

  private BookingRoomPaymentCompletedEvent paymentCompletedEventToBookingRoomStatusUpdatedEvent(final PaymentCompletedEvent event) {
    return BookingRoomPaymentCompletedEvent.builder()
      .reservationOrderId(event.getReservationOrderId())
      .customerId(event.getCustomerId())
      .status(CustomerReservationStatus.PAYMENT_CONFIRMED)
      .build();
  }
}
