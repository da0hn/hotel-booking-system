package com.hotel.booking.system.booking.service.core.ports.api.mapper;

import com.hotel.booking.system.booking.service.core.application.dto.BookingRoomInput;
import com.hotel.booking.system.booking.service.core.domain.entity.Booking;
import com.hotel.booking.system.commons.core.domain.event.BookingRoomRequestedEvent;

public interface BookingUseCaseMapper {
  BookingRoomInput bookingRoomRequestedEventToBookingRoomInput(
    BookingRoomRequestedEvent event
  );

  Booking bookingRoomInputToBooking(BookingRoomInput input);
}
