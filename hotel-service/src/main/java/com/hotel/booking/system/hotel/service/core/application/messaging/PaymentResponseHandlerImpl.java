package com.hotel.booking.system.hotel.service.core.application.messaging;

import com.hotel.booking.system.commons.core.domain.event.BookingRoomPaymentCompleted;
import com.hotel.booking.system.commons.core.domain.event.BookingRoomPaymentFailed;
import com.hotel.booking.system.commons.core.domain.event.PaymentCompletedEvent;
import com.hotel.booking.system.commons.core.domain.event.PaymentFailedEvent;
import com.hotel.booking.system.commons.core.domain.event.PaymentResponseEvent;
import com.hotel.booking.system.commons.core.domain.event.customer.CustomerBookingPaymentCompletedEvent;
import com.hotel.booking.system.commons.core.domain.event.customer.CustomerBookingPaymentFailedEvent;
import com.hotel.booking.system.commons.core.domain.valueobject.BookingStatus;
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
        final var customerBookingPaymentCompletedEvent = this.paymentCompletedEventToCustomerBookingPaymentCompletedEvent(e);
        this.customerBookingRoomStatusUpdatedPublisher.publish(customerBookingPaymentCompletedEvent);
        log.info("Customer service notified | reservationOrderId={}", e.getReservationOrderId());

        final var bookingRoomPaymentCompleted = this.paymentCompletedEventToBookingRoomPaymentCompleted(e);
        this.bookingRoomStatusChangedPublisher.publish(bookingRoomPaymentCompleted);
        log.info("Booking service notified | reservationOrderId={}", e.getReservationOrderId());
      }
      case final PaymentFailedEvent e -> {
        log.info(
          "Payment failed with error messages \"{}\". Notifying customer service & booking service | reservationOrderId={}",
          String.join(", ", e.getFailureMessages()),
          e.getReservationOrderId()
        );

        final var customerBookingPaymentFailedEvent = this.paymentFailedEventToCustomerBookingPaymentFailedEvent(e);
        this.customerBookingRoomStatusUpdatedPublisher.publish(customerBookingPaymentFailedEvent);
        log.info("Customer service notified | reservationOrderId={}", e.getReservationOrderId());

        final var bookingRoomPaymentFailedEvent = this.paymentFailedEventToBookingRoomPaymentFailedEvent(e);
        this.bookingRoomStatusChangedPublisher.publish(bookingRoomPaymentFailedEvent);
        log.info("Booking service notified | reservationOrderId={}", e.getReservationOrderId());
      }
      default -> throw new IllegalStateException(
        "Failed on handling sub-type of PaymentResponseEvent: Unknown event"
      );
    }
  }

  private BookingRoomPaymentCompleted paymentCompletedEventToBookingRoomPaymentCompleted(final PaymentCompletedEvent e) {
    return BookingRoomPaymentCompleted.builder()
      .reservationOrderId(e.getReservationOrderId())
      .status(BookingStatus.CONFIRMED)
      .customerId(e.getCustomerId())
      .build();
  }

  private BookingRoomPaymentFailed paymentFailedEventToBookingRoomPaymentFailedEvent(final PaymentFailedEvent e) {
    return BookingRoomPaymentFailed.builder()
      .reservationOrderId(e.getReservationOrderId())
      .status(BookingStatus.FAILED)
      .customerId(e.getCustomerId())
      .failureMessages(e.getFailureMessages())
      .build();
  }

  private CustomerBookingPaymentFailedEvent paymentFailedEventToCustomerBookingPaymentFailedEvent(final PaymentFailedEvent event) {
    return CustomerBookingPaymentFailedEvent.builder()
      .reservationOrderId(event.getReservationOrderId())
      .status(CustomerReservationStatus.PAYMENT_FAILED)
      .customerId(event.getCustomerId())
      .failureMessages(event.getFailureMessages())
      .build();
  }

  private CustomerBookingPaymentCompletedEvent paymentCompletedEventToCustomerBookingPaymentCompletedEvent(final PaymentCompletedEvent event) {
    return CustomerBookingPaymentCompletedEvent.builder()
      .reservationOrderId(event.getReservationOrderId())
      .customerId(event.getCustomerId())
      .status(CustomerReservationStatus.PAYMENT_CONFIRMED)
      .build();
  }

}
