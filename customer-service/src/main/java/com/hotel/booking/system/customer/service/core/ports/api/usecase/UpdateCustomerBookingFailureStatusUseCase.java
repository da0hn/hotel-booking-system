package com.hotel.booking.system.customer.service.core.ports.api.usecase;

import com.hotel.booking.system.customer.service.core.application.dto.UpdateCustomerBookingFailureStatusInput;

@FunctionalInterface
public interface UpdateCustomerBookingFailureStatusUseCase {

  void execute(UpdateCustomerBookingFailureStatusInput input);

}
