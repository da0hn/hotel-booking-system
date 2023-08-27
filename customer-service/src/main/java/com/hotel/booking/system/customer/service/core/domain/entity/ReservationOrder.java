package com.hotel.booking.system.customer.service.core.domain.entity;

import com.hotel.booking.system.commons.core.domain.AbstractDomainEntity;
import com.hotel.booking.system.commons.core.domain.valueobject.CustomerId;
import com.hotel.booking.system.commons.core.domain.valueobject.CustomerReservationStatus;
import com.hotel.booking.system.commons.core.domain.valueobject.HotelId;
import com.hotel.booking.system.commons.core.domain.valueobject.Money;
import com.hotel.booking.system.commons.core.domain.valueobject.ReservationOrderId;
import com.hotel.booking.system.customer.service.core.domain.valueobject.Timeline;

import java.time.LocalDate;

public class ReservationOrder extends AbstractDomainEntity<ReservationOrderId> {

  private final Timeline timeline = Timeline.empty();
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
    this.updateStatus(CustomerReservationStatus.AWAITING_RESERVATION);
  }


  public void updateStatus(final CustomerReservationStatus status) {
    this.currentStatus = status;
    this.timeline.add(ReservationOrderTimeline.update(status));
  }


}
