package com.hotel.booking.system.booking.service.data.db.entity;

import com.hotel.booking.system.booking.service.core.domain.valueobject.BookingStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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
import java.time.Instant;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "booking")
public class BookingEntity {
  @Id
  @JdbcTypeCode(SqlTypes.VARCHAR)
  @Column(name = "id", nullable = false, length = 36)
  private UUID id;

  @NotNull
  @JdbcTypeCode(SqlTypes.VARCHAR)
  @Column(name = "customer_id", nullable = false, length = 36)
  private UUID customerId;

  @NotNull
  @JdbcTypeCode(SqlTypes.VARCHAR)
  @Column(name = "reservation_order_id", nullable = false, length = 36)
  private UUID reservationOrderId;

  @NotNull
  @Column(name = "check_in", nullable = false)
  private LocalDate checkIn;

  @NotNull
  @Column(name = "check_out", nullable = false)
  private LocalDate checkOut;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false, length = 50)
  private BookingStatus status;

  @NotNull
  @Column(name = "total_price", nullable = false, precision = 10)
  private BigDecimal totalPrice;

  @Column(name = "created_at")
  private Instant createdAt;

  @Column(name = "updated_at")
  private Instant updatedAt;

  @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<BookingRoomEntity> bookingRooms = new LinkedHashSet<>();

  @PrePersist
  public void prePersist() {
    this.createdAt = Instant.now();
  }

  @PreUpdate
  public void preUpdate() {
    this.updatedAt = Instant.now();
  }
}
