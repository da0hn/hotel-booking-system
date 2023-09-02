package com.hotel.booking.system.booking.service.core.domain.entity;

import com.hotel.booking.system.booking.service.core.domain.valueobject.BookingId;
import com.hotel.booking.system.booking.service.core.domain.valueobject.BookingRoomId;
import com.hotel.booking.system.commons.core.domain.AbstractDomainEntity;
import com.hotel.booking.system.commons.core.domain.valueobject.Money;
import com.hotel.booking.system.commons.core.domain.valueobject.RoomId;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@SuperBuilder
public class BookingRoom extends AbstractDomainEntity<BookingRoomId> {

  private RoomId roomId;
  private BookingId bookingId;
  private Integer quantity;
  private Money price;


  public BookingRoom(
    final BookingRoomId id,
    final RoomId roomId,
    final BookingId bookingId,
    final Integer quantity,
    final Money price
  ) {
    super(id);
    this.roomId = roomId;
    this.bookingId = bookingId;
    this.quantity = quantity;
    this.price = price;
  }

  public void initialize(final BookingId bookingId) {
    this.setId(BookingRoomId.newInstance());
    this.bookingId = bookingId;
  }

  public void validate() {

  }

  public Money getPrice() {
    return this.price;
  }

  public Integer getQuantity() {
    return this.quantity;
  }

  public Money getTotalPrice() {
    return this.price.multiply(new BigDecimal(this.quantity));
  }

  public RoomId getRoomId() {
    return this.roomId;
  }
}
