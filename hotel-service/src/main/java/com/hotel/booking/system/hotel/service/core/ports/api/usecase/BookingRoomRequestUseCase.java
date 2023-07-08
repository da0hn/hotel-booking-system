package com.hotel.booking.system.hotel.service.core.ports.api.usecase;

import com.hotel.booking.system.hotel.service.core.application.dto.BookingRoomInput;
import com.hotel.booking.system.hotel.service.core.application.dto.BookingRoomOutput;

@FunctionalInterface
public interface BookingRoomRequestUseCase {

  BookingRoomOutput execute(BookingRoomInput input);

}
