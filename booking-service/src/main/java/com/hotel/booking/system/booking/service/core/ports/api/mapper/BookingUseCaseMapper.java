package com.hotel.booking.system.booking.service.core.ports.api.mapper;

import com.hotel.booking.system.booking.service.core.application.dto.BookingRoomInput;
import com.hotel.booking.system.booking.service.core.application.dto.BookingRoomOutput;
import com.hotel.booking.system.booking.service.core.application.dto.UpdateBookingStatusInput;
import com.hotel.booking.system.booking.service.core.domain.entity.Booking;
import com.hotel.booking.system.commons.core.domain.event.BookingRoomFailedEvent;
import com.hotel.booking.system.commons.core.domain.event.BookingRoomPendingEvent;
import com.hotel.booking.system.commons.core.domain.event.BookingRoomRequestedEvent;
import com.hotel.booking.system.commons.core.domain.event.BookingRoomStatusUpdatedEvent;

public interface BookingUseCaseMapper {
  BookingRoomInput bookingRoomRequestedEventToBookingRoomInput(
    BookingRoomRequestedEvent event
  );

  Booking bookingRoomInputToBooking(BookingRoomInput input);

  BookingRoomFailedEvent bookingRoomOutputToBookingRoomFailedEvent(BookingRoomOutput output);

  BookingRoomPendingEvent bookingRoomOutputToBookingRoomResponseEvent(BookingRoomOutput output);

  UpdateBookingStatusInput bookingRoomStatusUpdatedEventToUpdateBookingRoomStatusInput(BookingRoomStatusUpdatedEvent event);
}
