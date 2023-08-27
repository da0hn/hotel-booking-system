package com.hotel.booking.system.customer.service.core.ports.api.usecase;

import com.hotel.booking.system.customer.service.core.application.dto.UpdateCustomerBookingStatusInput;

@FunctionalInterface
public interface UpdateCustomerBookingStatusUseCase {

  void execute(UpdateCustomerBookingStatusInput input);

}
