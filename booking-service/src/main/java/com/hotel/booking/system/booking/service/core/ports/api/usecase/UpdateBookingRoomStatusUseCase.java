package com.hotel.booking.system.booking.service.core.ports.api.usecase;

import com.hotel.booking.system.booking.service.core.application.dto.UpdateBookingRoomStatusInput;
import com.hotel.booking.system.booking.service.core.application.dto.UpdateBookingRoomStatusOutput;

@FunctionalInterface
public interface UpdateBookingRoomStatusUseCase {

  UpdateBookingRoomStatusOutput execute(UpdateBookingRoomStatusInput input);

}
