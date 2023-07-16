package com.hotel.booking.system.booking.service.data.db.mapper;

import com.hotel.booking.system.booking.service.core.domain.entity.Booking;
import com.hotel.booking.system.booking.service.data.db.entity.BookingEntity;

public interface BookingDatabaseMapper {
  Booking bookingEntityToBooking(BookingEntity bookingEntity);
}
