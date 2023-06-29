package com.hotel.booking.system.hotel.service.data.db.repository;

import com.hotel.booking.system.hotel.service.data.db.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoomJpaRepository extends JpaRepository<RoomEntity, UUID> {
}
