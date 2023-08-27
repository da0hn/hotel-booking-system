package com.hotel.booking.system.commons.core.message;

public final class ApplicationMessage {

  public static final String HOTEL_NOT_NULL = "hotel.must.not.be.null";
  public static final String HOTEL_CATEGORY_NOT_NULL = "hotel.category.must.not.be.null";
  public static final String HOTEL_LOCALITY_NOT_NULL = "hotel.locality.must.not.be.null";
  public static final String HOTEL_NAME_INVALID = "hotel.name.must.not.be.null-or-empty";
  public static final String HOTEL_DESCRIPTION_INVALID = "hotel.description.must.not.be.null-or-empty";
  public static final String HOTEL_STREET_INVALID = "hotel.street.must.not.be.null-or-empty";
  public static final String HOTEL_CEP_INVALID = "hotel.cep.invalid";
  public static final String HOTEL_CATEGORY_NOT_FOUND = "hotel.category.not.found";
  public static final String HOTEL_LOCALITY_NOT_FOUND = "hotel.locality.not.found";
  public static final String HOTEL_ROOMS_INVALID = "hotel.rooms.must.not.be.null-or-empty";
  public static final String HOTEL_ROOM_RELATIONSHIP_NOT_FOUND = "hotel.room.relationship.not.found";
  public static final String HOTEL_ROOM_CAPACITY_NOT_NULL = "hotel.room.capacity.must.not.be.null";
  public static final String HOTEL_ROOM_CAPACITY_EXCEEDED = "hotel.room.capacity.exceeded";
  public static final String HOTEL_ROOM_CAPACITY_INVALID = "hotel.room.capacity.must.not.be.zero-or-negative";
  public static final String HOTEL_ROOM_CURRENT_PRICE_INVALID = "hotel.room.current-price.must.not.be.zero-or-negative";
  public static final String HOTEL_ROOM_CURRENT_PRICE_NOT_NULL = "hotel.room.current-price.must.not.be.null";
  public static final String HOTEL_ROOM_NAME_NOT_NULL = "hotel.room.name.must.not.be.null";
  public static final String HOTEL_ROOM_DESCRIPTION_NOT_NULL = "hotel.room.description.must.not.be.null";
  public static final String HOTEL_ROOM_NOT_NULL = "hotel.room.must.not.be.null";
  public static final String HOTEL_ROOM_QUANTITY_NOT_NULL = "hotel.room.quantity.must.not.be.null";
  public static final String HOTEL_ROOM_QUANTITY_INVALID = "hotel.room.quantity.must.not.be.zero-or-negative";
  public static final String HOTEL_ROOM_NOT_FOUND = "hotel.room.not.found";
  public static final String HOTEL_BOOKING_GUESTS_EXCEEDED = "hotel.booking.guests.exceeded";
  public static final String BOOKING_NOT_NULL = "booking.must.be.not.null";
  public static final String BOOKING_ROOM_NOT_NULL = "booking.room.must.not.be.null";
  public static final String BOOKING_RESERVATION_ORDER_INVALID = "booking.reservation.order.must.not.be.null";
  public static final String BOOKING_PERIOD_CHECK_IN_NOT_NULL = "booking.period.check-in.must.not.be.null";
  public static final String BOOKING_PERIOD_CHECK_OUT_NOT_NULL = "booking.period.check-out.must.not.be.null";
  public static final String BOOKING_PERIOD_CHECK_IN_AFTER_CHECK_OUT = "booking.period.check-in.after.check-out";
  public static final String BOOKING_CUSTOMER_NOT_NULL = "booking.customer.must.not.be.null";
  public static final String BOOKING_TOTAL_PRICE_INVALID = "booking.total-price.invalid";
  public static final String BOOKING_STATUS_NOT_NULL = "booking.status.must.not.be.null";
  public static final String BOOKING_NOT_FOUND = "booking.not.found";
  public static final String CUSTOMER_RESERVATION_ORDER_TIMELINE_NOT_NULL = "customer.reservation-order.timeline.must.not.be.null";
  public static final String CUSTOMER_RESERVATION_ORDER_STATUS_INVALID_STATE = "customer.reservation-order.status.invalid-state";
  public static final String CUSTOMER_NOT_FOUND = "customer.not.found";


  private ApplicationMessage() {
  }

}
