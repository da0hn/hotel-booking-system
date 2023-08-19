package com.hotel.booking.system.payment.service.core.application.mapper;

import com.hotel.booking.system.commons.core.domain.event.PaymentCompletedEvent;
import com.hotel.booking.system.commons.core.domain.event.PaymentFailedEvent;
import com.hotel.booking.system.commons.core.domain.event.PaymentRequestedEvent;
import com.hotel.booking.system.commons.core.domain.event.PaymentResponseEvent;
import com.hotel.booking.system.commons.core.domain.valueobject.CustomerId;
import com.hotel.booking.system.commons.core.domain.valueobject.Money;
import com.hotel.booking.system.commons.core.domain.valueobject.ReservationOrderId;
import com.hotel.booking.system.payment.service.core.application.dto.PayOrderInput;
import com.hotel.booking.system.payment.service.core.application.dto.PayOrderOutput;
import com.hotel.booking.system.payment.service.core.domain.Payment;
import com.hotel.booking.system.payment.service.core.ports.api.mapper.PaymentUseCaseMapper;

public class PaymentUseCaseMapperImpl implements PaymentUseCaseMapper {

  @Override
  public PayOrderInput paymentRequestedEventToPayOrderInput(final PaymentRequestedEvent event) {
    return PayOrderInput.builder()
      .reservationOrderId(event.getReservationOrderId())
      .bookingRoomId(event.getBookingRoomId())
      .customerId(event.getCustomerId())
      .totalPrice(event.getTotalPrice())
      .build();
  }

  @Override
  public Payment payOrderInputToPayment(final PayOrderInput input) {
    return Payment.builder()
      .reservationOrderId(ReservationOrderId.of(input.reservationOrderId()))
      .customerId(CustomerId.of(input.customerId()))
      .totalPrice(Money.of(input.totalPrice()))
      .build();
  }

  @Override
  public PaymentResponseEvent payOrderOutputToPaymentFailedEvent(final PayOrderOutput output) {
    return PaymentFailedEvent.builder()
      .reservationOrderId(output.payment().getReservationOrderId().toString())
      .customerId(output.payment().getCustomerId().toString())
      .totalPrice(output.payment().getTotalPrice().getValue())
      .failureMessages(output.failureMessages())
      .status(output.status())
      .build();
  }

  @Override
  public PaymentResponseEvent payOrderOutputToPaymentCompletedEvent(final PayOrderOutput output) {
    return PaymentCompletedEvent.builder()
      .reservationOrderId(output.payment().getReservationOrderId().toString())
      .customerId(output.payment().getCustomerId().toString())
      .totalPrice(output.payment().getTotalPrice().getValue())
      .status(output.status())
      .build();
  }

}
