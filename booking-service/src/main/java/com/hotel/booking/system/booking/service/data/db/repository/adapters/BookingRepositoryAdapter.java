package com.hotel.booking.system.booking.service.data.db.repository.adapters;

import com.hotel.booking.system.booking.service.core.domain.entity.Booking;
import com.hotel.booking.system.booking.service.core.domain.entity.BookingPeriod;
import com.hotel.booking.system.booking.service.core.ports.spi.repository.BookingRepository;
import com.hotel.booking.system.booking.service.data.db.mapper.BookingDatabaseMapper;
import com.hotel.booking.system.booking.service.data.db.repository.BookingJpaRepository;
import com.hotel.booking.system.commons.core.domain.valueobject.RoomId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class BookingRepositoryAdapter implements BookingRepository {

  private final BookingJpaRepository bookingJpaRepository;
  private final BookingDatabaseMapper bookingDatabaseMapper;

  @Override
  public List<Booking> findBookingByRoomIdAndPeriod(final RoomId roomId, final BookingPeriod bookingPeriod) {
    final var bookings = this.bookingJpaRepository.findByRoomIdAndPeriod(
      roomId.getValue(),
      bookingPeriod.getCheckIn(),
      bookingPeriod.getCheckOut()
    );
    return bookings.stream()
      .map(this.bookingDatabaseMapper::bookingEntityToBooking)
      .collect(Collectors.toList());
  }
}
