package com.hotel.booking.system.booking.service.data.db.mapper;

import com.hotel.booking.system.booking.service.core.domain.entity.Booking;
import com.hotel.booking.system.booking.service.core.domain.entity.Room;
import com.hotel.booking.system.booking.service.data.db.entity.BookingEntity;
import com.hotel.booking.system.booking.service.data.db.entity.RoomEntity;

public interface BookingDatabaseMapper {

  Booking bookingEntityToBooking(BookingEntity bookingEntity);

  BookingEntity bookingToBookingEntity(Booking booking);

  Room roomEntityToRoom(RoomEntity roomEntity);
}
