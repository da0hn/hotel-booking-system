package com.hotel.booking.system.booking.service.data.db.repository;

import com.hotel.booking.system.booking.service.data.db.entity.BookingRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookingRoomJpaRepository extends JpaRepository<BookingRoomEntity, UUID> {
}
