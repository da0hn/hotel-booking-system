package com.hotel.booking.system.payment.service.core.application.messaging;

import com.hotel.booking.system.commons.core.domain.event.PaymentRequestedEvent;
import com.hotel.booking.system.commons.core.domain.event.PaymentResponseEvent;
import com.hotel.booking.system.payment.service.core.application.dto.PayOrderOutput;
import com.hotel.booking.system.payment.service.core.ports.api.mapper.PaymentUseCaseMapper;
import com.hotel.booking.system.payment.service.core.ports.api.messaging.PaymentRequestedHandler;
import com.hotel.booking.system.payment.service.core.ports.api.usecase.PayOrderUseCase;
import com.hotel.booking.system.payment.service.core.ports.spi.messaging.publisher.PaymentResponsePublisher;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PaymentRequestedHandlerImpl implements PaymentRequestedHandler {

  private final PayOrderUseCase payOrderUseCase;
  private final PaymentUseCaseMapper mapper;
  private final PaymentResponsePublisher publisher;

  public PaymentRequestedHandlerImpl(
    final PayOrderUseCase payOrderUseCase,
    final PaymentUseCaseMapper mapper,
    final PaymentResponsePublisher publisher
  ) {
    this.payOrderUseCase = payOrderUseCase;
    this.mapper = mapper;
    this.publisher = publisher;
  }

  @Override
  public void handle(final PaymentRequestedEvent event) {
    log.info(
      "Payment request received, initiating payment for reservationOrderId={}, customerId={}",
      event.getReservationOrderId(),
      event.getCustomerId()
    );

    final var input = this.mapper.paymentRequestedEventToPayOrderInput(event);

    final var output = this.payOrderUseCase.execute(input);

    final var paymentResponseEvent = this.mapOutputToEvent(output);

    this.publisher.publish(paymentResponseEvent);
  }

  private PaymentResponseEvent mapOutputToEvent(final PayOrderOutput output) {
    if (output.failureMessages().isNotEmpty()) {
      return this.mapper.payOrderOutputToPaymentFailedEvent(output);
    } else {
      return this.mapper.payOrderOutputToPaymentCompletedEvent(output);
    }
  }

}
