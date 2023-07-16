package com.hotel.booking.system.booking.service.core.ports.api.usecase;

import com.hotel.booking.system.booking.service.core.application.dto.BookingRoomInput;
import com.hotel.booking.system.booking.service.core.application.dto.BookingRoomOutput;

@FunctionalInterface
public interface BookingRoomUseCase {

  BookingRoomOutput execute(BookingRoomInput input);

}
