package com.hotel.booking.system.customer.service.data.db.entity;

import com.hotel.booking.system.commons.core.domain.valueobject.CustomerReservationStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reservation_order_history")
public class ReservationOrderHistoryEntity implements Serializable {
  @Serial
  private static final long serialVersionUID = 3405171944021718782L;

  @Id
  @JdbcTypeCode(SqlTypes.VARCHAR)
  @Column(name = "id", nullable = false, length = 36)
  private UUID id;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false, length = 50)
  private CustomerReservationStatus status;

  @NotNull
  @Column(name = "occurred_at", nullable = false)
  private Instant occurredAt;

  @ManyToOne
  @JoinColumn(name = "reservation_order_id")
  private ReservationOrderEntity reservationOrder;

}
