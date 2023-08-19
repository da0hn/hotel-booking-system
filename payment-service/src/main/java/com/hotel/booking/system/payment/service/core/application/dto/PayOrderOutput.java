package com.hotel.booking.system.payment.service.core.application.dto;

import com.hotel.booking.system.commons.core.domain.valueobject.FailureMessages;
import com.hotel.booking.system.commons.core.domain.valueobject.PaymentStatus;
import com.hotel.booking.system.payment.service.core.domain.Payment;
import lombok.Builder;

@Builder
public record PayOrderOutput(
  Payment payment,
  PaymentStatus status,
  FailureMessages failureMessages
) {
}
