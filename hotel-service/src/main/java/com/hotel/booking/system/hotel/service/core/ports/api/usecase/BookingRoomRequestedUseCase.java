package com.hotel.booking.system.hotel.service.core.ports.api.usecase;

import com.hotel.booking.system.hotel.service.core.application.dto.BookRoomInput;
import com.hotel.booking.system.hotel.service.core.application.dto.BookRoomOutput;

@FunctionalInterface
public interface BookingRoomRequestedUseCase {

  BookRoomOutput execute(BookRoomInput input);

}
