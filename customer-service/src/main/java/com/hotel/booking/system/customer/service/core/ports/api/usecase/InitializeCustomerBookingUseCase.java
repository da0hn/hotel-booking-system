package com.hotel.booking.system.customer.service.core.ports.api.usecase;

import com.hotel.booking.system.customer.service.core.application.dto.InitializeReservationOrderInput;

@FunctionalInterface
public interface InitializeCustomerBookingUseCase {

  void execute(InitializeReservationOrderInput input);

}
