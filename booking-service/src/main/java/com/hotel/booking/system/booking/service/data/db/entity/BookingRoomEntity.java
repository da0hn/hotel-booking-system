package com.hotel.booking.system.booking.service.data.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "booking_room")
public class BookingRoomEntity {
  @Id
  @JdbcTypeCode(SqlTypes.VARCHAR)
  @Column(name = "id", nullable = false, length = 36)
  private UUID id;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "room_id", nullable = false)
  private RoomEntity room;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "booking_id", nullable = false)
  private BookingEntity booking;

  @NotNull
  @Column(name = "price", nullable = false, precision = 10)
  private BigDecimal price;

  @NotNull
  @Column(name = "quantity", nullable = false)
  private Integer quantity;

}
