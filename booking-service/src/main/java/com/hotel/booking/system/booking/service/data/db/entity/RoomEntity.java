package com.hotel.booking.system.booking.service.data.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "room")
public class RoomEntity {
  @Id
  @JdbcTypeCode(SqlTypes.VARCHAR)
  @Column(name = "id", nullable = false, length = 36)
  private UUID id;

  @NotNull
  @JdbcTypeCode(SqlTypes.VARCHAR)
  @Column(name = "hotel_id", nullable = false, length = 36)
  private UUID hotelId;

  @NotNull
  @Column(name = "current_price", nullable = false, precision = 10)
  private BigDecimal currentPrice;

  @NotNull
  @Column(name = "capacity", nullable = false)
  private Integer capacity;

  @NotNull
  @Column(name = "quantity", nullable = false)
  private Integer quantity;

}
