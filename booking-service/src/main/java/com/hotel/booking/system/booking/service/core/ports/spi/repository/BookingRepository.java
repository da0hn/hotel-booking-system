package com.hotel.booking.system.booking.service.core.ports.spi.repository;

import com.hotel.booking.system.booking.service.core.domain.entity.Booking;
import com.hotel.booking.system.booking.service.core.domain.entity.BookingPeriod;
import com.hotel.booking.system.commons.core.domain.valueobject.RoomId;

import java.util.List;

public interface BookingRepository {

  List<Booking> findBookingByRoomIdAndPeriod(RoomId roomId, BookingPeriod bookingPeriod);

}
