package com.hotel.booking.system.payment.service.core.domain;

import com.hotel.booking.system.commons.core.domain.AbstractDomainEntity;
import com.hotel.booking.system.commons.core.domain.valueobject.CustomerId;
import com.hotel.booking.system.commons.core.domain.valueobject.Money;
import com.hotel.booking.system.commons.core.domain.valueobject.PaymentId;
import com.hotel.booking.system.commons.core.domain.valueobject.PaymentStatus;
import com.hotel.booking.system.commons.core.domain.valueobject.ReservationOrderId;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
public class Payment extends AbstractDomainEntity<PaymentId> {

  private LocalDateTime createdAt;
  private ReservationOrderId reservationOrderId;
  private CustomerId customerId;
  private PaymentStatus status;
  private Money totalPrice;

  public void initialize() {
    this.setId(PaymentId.newInstance());
    this.createdAt = LocalDateTime.now();
  }

  public LocalDateTime getCreatedAt() {
    return this.createdAt;
  }

  public ReservationOrderId getReservationOrderId() {
    return this.reservationOrderId;
  }

  public CustomerId getCustomerId() {
    return this.customerId;
  }

  public PaymentStatus getStatus() {
    return this.status;
  }

  public Money getTotalPrice() {
    return this.totalPrice;
  }
}
