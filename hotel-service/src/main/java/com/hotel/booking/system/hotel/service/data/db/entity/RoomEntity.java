package com.hotel.booking.system.hotel.service.data.db.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@Table(name = "room")
public class RoomEntity implements Serializable {

  @Serial
  private static final long serialVersionUID = -8682110681781633045L;

  @Id
  @Size(max = 36)
  @Column(name = "id", nullable = false, length = 36)
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Size(max = 30)
  @NotNull
  @Column(name = "name", nullable = false, length = 30)
  private String name;

  @Size(max = 100)
  @NotNull
  @Column(name = "description", nullable = false, length = 100)
  private String description;

  @NotNull
  @Column(name = "capacity", nullable = false)
  private Integer capacity;

  @NotNull
  @Column(name = "current_price", nullable = false, precision = 10)
  private BigDecimal currentPrice;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "hotel_id", nullable = false)
  private HotelEntity hotel;

}
