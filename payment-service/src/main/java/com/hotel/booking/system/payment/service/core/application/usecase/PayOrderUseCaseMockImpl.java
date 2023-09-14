package com.hotel.booking.system.payment.service.core.application.usecase;

import com.hotel.booking.system.commons.core.domain.valueobject.FailureMessages;
import com.hotel.booking.system.commons.core.domain.valueobject.PaymentStatus;
import com.hotel.booking.system.payment.service.core.application.dto.PayOrderInput;
import com.hotel.booking.system.payment.service.core.application.dto.PayOrderOutput;
import com.hotel.booking.system.payment.service.core.ports.api.mapper.PaymentUseCaseMapper;
import com.hotel.booking.system.payment.service.core.ports.api.usecase.PayOrderUseCase;
import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class PayOrderUseCaseMockImpl implements PayOrderUseCase {

  private final PaymentUseCaseMapper paymentUseCaseMapper;

  private final Integer minValue;

  private final Integer maxValue;

  public PayOrderUseCaseMockImpl(
    final PaymentUseCaseMapper paymentUseCaseMapper,
    final Integer minValue,
    final Integer maxValue
  ) {
    this.paymentUseCaseMapper = paymentUseCaseMapper;
    this.minValue = minValue;
    this.maxValue = maxValue;
  }

  @Override
  @SneakyThrows
  public PayOrderOutput execute(final PayOrderInput input) {

    final var failureMessages = FailureMessages.empty();
    final var payment = this.paymentUseCaseMapper.payOrderInputToPayment(input);
    payment.initialize();

    TimeUnit.SECONDS.sleep(1); // Simulate payment connection delay

    this.pay(failureMessages);

    TimeUnit.SECONDS.sleep(1); // Simulate payment connection delay

    if (failureMessages.isNotEmpty()) {
      return new PayOrderOutput(
        payment,
        PaymentStatus.FAILED,
        failureMessages
      );
    }

    return new PayOrderOutput(
      payment,
      PaymentStatus.COMPLETED,
      failureMessages
    );
  }

  private void pay(final FailureMessages failureMessages) {
    final var successfullyPaid = new Random().ints(this.minValue, this.maxValue)
      .findFirst()
      .stream()
      .anyMatch(value -> value % 2 == 0);

    if (successfullyPaid) return;

    failureMessages.add("Customer doesn't have enough credit for payment");
  }

}
