package com.hotel.booking.system.payment.service.core.ports.api.usecase;

import com.hotel.booking.system.payment.service.core.application.dto.PayOrderInput;
import com.hotel.booking.system.payment.service.core.application.dto.PayOrderOutput;

@FunctionalInterface
public interface PayOrderUseCase {

  PayOrderOutput execute(PayOrderInput input);

}
