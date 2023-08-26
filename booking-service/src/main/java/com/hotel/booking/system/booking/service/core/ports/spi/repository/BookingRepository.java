package com.hotel.booking.system.booking.service.core.ports.spi.repository;

import com.hotel.booking.system.booking.service.core.domain.entity.Booking;
import com.hotel.booking.system.booking.service.core.domain.entity.BookingPeriod;
import com.hotel.booking.system.commons.core.domain.valueobject.ReservationOrderId;
import com.hotel.booking.system.commons.core.domain.valueobject.RoomId;

import java.util.List;
import java.util.Optional;

public interface BookingRepository {

  List<Booking> findBookingByRoomIdAndPeriod(RoomId roomId, BookingPeriod bookingPeriod);

  void save(Booking booking);

  Optional<Booking> findBookingByReservationOrderId(ReservationOrderId reservationOrderId);
}
