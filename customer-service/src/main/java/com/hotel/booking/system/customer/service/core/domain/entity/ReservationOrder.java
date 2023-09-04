package com.hotel.booking.system.customer.service.core.domain.entity;

import com.hotel.booking.system.commons.core.domain.AbstractDomainEntity;
import com.hotel.booking.system.commons.core.domain.valueobject.CustomerId;
import com.hotel.booking.system.commons.core.domain.valueobject.CustomerReservationStatus;
import com.hotel.booking.system.commons.core.domain.valueobject.FailureMessages;
import com.hotel.booking.system.commons.core.domain.valueobject.HotelId;
import com.hotel.booking.system.commons.core.domain.valueobject.Money;
import com.hotel.booking.system.commons.core.domain.valueobject.ReservationOrderId;
import com.hotel.booking.system.commons.core.message.ApplicationMessage;
import com.hotel.booking.system.customer.service.core.domain.exception.CustomerDomainException;
import com.hotel.booking.system.customer.service.core.domain.valueobject.Timeline;
import lombok.Builder;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Objects;

@SuperBuilder
public class ReservationOrder extends AbstractDomainEntity<ReservationOrderId> {

  @Builder.Default
  private Timeline timeline = Timeline.empty();
  private CustomerId customerId;
  private HotelId hotelId;
  private Integer guests;
  private LocalDate checkIn;
  private LocalDate checkOut;
  private Money totalPrice;
  private CustomerReservationStatus currentStatus;


  protected ReservationOrder(final ReservationOrderId id) {
    super(id);
  }

  public void initialize() {
    if (Objects.isNull(this.getId())) {
      this.setId(ReservationOrderId.newInstance());
    }
    this.updateStatus(CustomerReservationStatus.AWAITING_RESERVATION);
  }


  public void updateStatus(final CustomerReservationStatus status) {
    this.currentStatus = status;
    this.timeline.add(ReservationOrderTimeline.update(status));
  }

  public void updateToFailureStatus(final CustomerReservationStatus status, FailureMessages failureMessages) {
    if (!CustomerReservationStatus.isFailureStatus(status)) {
      throw new CustomerDomainException(ApplicationMessage.CUSTOMER_RESERVATION_ORDER_STATUS_INVALID_STATE);
    }
    this.currentStatus = status;
    this.timeline.add(ReservationOrderTimeline.update(status, failureMessages));
  }

  public Timeline getTimeline() {
    return this.timeline;
  }

  public CustomerId getCustomerId() {
    return this.customerId;
  }

  public HotelId getHotelId() {
    return this.hotelId;
  }

  public Integer getGuests() {
    return this.guests;
  }

  public LocalDate getCheckIn() {
    return this.checkIn;
  }

  public LocalDate getCheckOut() {
    return this.checkOut;
  }

  public Money getTotalPrice() {
    return this.totalPrice;
  }

  public CustomerReservationStatus getCurrentStatus() {
    return this.currentStatus;
  }

}
