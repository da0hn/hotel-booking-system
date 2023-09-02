package com.hotel.booking.system.booking.service.core.domain.entity;

import com.hotel.booking.system.booking.service.core.domain.exception.BookingDomainException;
import com.hotel.booking.system.booking.service.core.domain.valueobject.BookingId;
import com.hotel.booking.system.commons.core.domain.AbstractDomainEntity;
import com.hotel.booking.system.commons.core.domain.valueobject.BookingStatus;
import com.hotel.booking.system.commons.core.domain.valueobject.CustomerId;
import com.hotel.booking.system.commons.core.domain.valueobject.Money;
import com.hotel.booking.system.commons.core.domain.valueobject.ReservationOrderId;
import com.hotel.booking.system.commons.core.domain.valueobject.RoomId;
import com.hotel.booking.system.commons.core.message.ApplicationMessage;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@SuperBuilder
public class Booking extends AbstractDomainEntity<BookingId> {

  private final ReservationOrderId reservationOrderId;
  private final CustomerId customerId;
  private final BookingPeriod bookingPeriod;
  private final Money totalPrice;
  private final List<BookingRoom> bookingRooms;
  private final Integer guests;
  private Instant createdAt;
  private Instant updatedAt;
  private BookingStatus status;

  public Booking(
    final BookingId id,
    final ReservationOrderId reservationOrderId,
    final CustomerId customerId,
    final BookingPeriod bookingPeriod,
    final Money totalPrice,
    final List<BookingRoom> bookingRooms,
    final Integer guests,
    final BookingStatus status
  ) {
    super(id);
    this.reservationOrderId = reservationOrderId;
    this.customerId = customerId;
    this.bookingPeriod = bookingPeriod;
    this.totalPrice = totalPrice;
    this.bookingRooms = Optional.ofNullable(bookingRooms)
      .map(ArrayList::new)
      .orElseGet(ArrayList::new);
    this.guests = guests;
    this.status = status;
  }

  public void initialize() {
    this.setId(BookingId.newInstance());
    this.status = BookingStatus.PENDING;
    this.bookingRooms.forEach(room -> room.initialize(this.getId()));
  }

  public void validate() {
    this.validateReservationOrderId();
    this.bookingPeriod.validate();
    this.validateCustomerId();
    this.validateTotalPrice();
    this.validateBookingRooms();
    this.validateStatus();
  }

  private void validateStatus() {
    if (this.status == null) {
      throw new BookingDomainException(ApplicationMessage.BOOKING_STATUS_NOT_NULL);
    }
  }

  private void validateBookingRooms() {
    this.bookingRooms.forEach(BookingRoom::validate);
  }

  private void validateTotalPrice() {
    final var totalItensPrice = this.bookingRooms.stream()
      .map(BookingRoom::getTotalPrice)
      .reduce(Money.ZERO, Money::add);
    if (totalItensPrice.isNotEqual(this.totalPrice)) {
      throw new BookingDomainException(ApplicationMessage.BOOKING_TOTAL_PRICE_INVALID);
    }
  }

  private void validateCustomerId() {
    if (this.customerId == null || this.customerId.empty()) {
      throw new BookingDomainException(ApplicationMessage.BOOKING_CUSTOMER_NOT_NULL);
    }
  }

  private void validateReservationOrderId() {
    if (this.reservationOrderId == null || this.reservationOrderId.empty()) {
      throw new BookingDomainException(ApplicationMessage.BOOKING_RESERVATION_ORDER_INVALID);
    }
  }

  public List<BookingRoom> getBookingRooms() {
    return this.bookingRooms;
  }

  public BookingPeriod getBookingPeriod() {
    return this.bookingPeriod;
  }

  public boolean isBookingPeriodContainedIn(final BookingPeriod period) {
    return this.bookingPeriod.isContainedIn(period);
  }

  public CustomerId getCustomerId() {
    return this.customerId;
  }

  public ReservationOrderId getReservationOrderId() {
    return this.reservationOrderId;
  }

  public Money getTotalPrice() {
    return this.totalPrice;
  }

  public BookingStatus getStatus() {
    return this.status;
  }

  public Instant getCreatedAt() {
    return this.createdAt;
  }

  public Instant getUpdatedAt() {
    return this.updatedAt;
  }

  public void changeStatusTo(final BookingStatus status) {
    Objects.requireNonNull(status, ApplicationMessage.BOOKING_STATUS_NOT_NULL);
    this.status = status;
  }

  public List<RoomId> getRoomsId() {
    return this.bookingRooms.stream()
      .map(BookingRoom::getRoomId)
      .toList();
  }
}
