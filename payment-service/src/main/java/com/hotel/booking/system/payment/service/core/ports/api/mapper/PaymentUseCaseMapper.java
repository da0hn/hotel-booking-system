package com.hotel.booking.system.payment.service.core.ports.api.mapper;

import com.hotel.booking.system.commons.core.domain.event.PaymentRequestedEvent;
import com.hotel.booking.system.commons.core.domain.event.PaymentResponseEvent;
import com.hotel.booking.system.payment.service.core.application.dto.PayOrderInput;
import com.hotel.booking.system.payment.service.core.application.dto.PayOrderOutput;
import com.hotel.booking.system.payment.service.core.domain.Payment;

public interface PaymentUseCaseMapper {
  PayOrderInput paymentRequestedEventToPayOrderInput(PaymentRequestedEvent event);

  Payment payOrderInputToPayment(PayOrderInput input);

  PaymentResponseEvent payOrderOutputToPaymentFailedEvent(PayOrderOutput output);

  PaymentResponseEvent payOrderOutputToPaymentCompletedEvent(PayOrderOutput output);
}
