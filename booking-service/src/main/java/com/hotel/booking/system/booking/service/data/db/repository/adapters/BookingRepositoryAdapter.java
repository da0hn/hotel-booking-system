package com.hotel.booking.system.booking.service.data.db.repository.adapters;

import com.hotel.booking.system.booking.service.core.domain.entity.Booking;
import com.hotel.booking.system.booking.service.core.domain.entity.BookingPeriod;
import com.hotel.booking.system.booking.service.core.ports.spi.repository.BookingRepository;
import com.hotel.booking.system.commons.core.domain.valueobject.RoomId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class BookingRepositoryAdapter implements BookingRepository {


  @Override
  public List<Booking> findBookingByRoomIdAndPeriod(final RoomId roomId, BookingPeriod bookingPeriod) {
    return null;
  }
}
