package com.hotel.booking.system.booking.service.data.db.repository;

import com.hotel.booking.system.booking.service.data.db.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface BookingJpaRepository extends JpaRepository<BookingEntity, UUID> {
  @Query("""
      SELECT booking FROM BookingEntity booking
      JOIN booking.bookingRooms bookingRooms
      WHERE
        bookingRooms.room.id = :roomId and
        (
          ( :checkIn between booking.checkIn and booking.checkOut ) or
          ( :checkOut between booking.checkIn and booking.checkOut )
        )
    """)
  List<BookingEntity> findByRoomIdAndPeriod(UUID roomId, LocalDate checkIn, LocalDate checkOut);
}
