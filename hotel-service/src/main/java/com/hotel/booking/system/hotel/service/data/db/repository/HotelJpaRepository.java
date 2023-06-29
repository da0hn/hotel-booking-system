package com.hotel.booking.system.hotel.service.data.db.repository;

import com.hotel.booking.system.hotel.service.data.db.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HotelJpaRepository extends JpaRepository<HotelEntity, UUID> {
  @Query(
    "select (count(hotel) > 0) " +
    "from HotelEntity hotel " +
    "where hotel.category.id = :categoryId"
  )
  boolean existsByCategoryId(UUID categoryId);
}
