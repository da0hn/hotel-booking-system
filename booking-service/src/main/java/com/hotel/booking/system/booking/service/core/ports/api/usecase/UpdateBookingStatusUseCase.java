package com.hotel.booking.system.booking.service.core.ports.api.usecase;

import com.hotel.booking.system.booking.service.core.application.dto.UpdateBookingStatusInput;
import com.hotel.booking.system.booking.service.core.application.dto.UpdateBookingStatusOutput;

@FunctionalInterface
public interface UpdateBookingStatusUseCase {

  UpdateBookingStatusOutput execute(UpdateBookingStatusInput input);

}
