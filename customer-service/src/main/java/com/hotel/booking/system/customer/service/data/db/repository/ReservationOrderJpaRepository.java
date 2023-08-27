package com.hotel.booking.system.customer.service.data.db.repository;

import com.hotel.booking.system.customer.service.data.db.entity.ReservationOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReservationOrderJpaRepository extends JpaRepository<ReservationOrderEntity, UUID> {
}
