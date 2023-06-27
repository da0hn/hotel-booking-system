package com.hotel.booking.system.commons.core.message;

public final class ApplicationMessage {

  public static final String HOTEL_CATEGORY_NOT_NULL = "hotel.category.must.not.be.null";
  public static final String HOTEL_LOCALITY_NOT_NULL = "hotel.locality.must.not.be.null";
  public static final String HOTEL_NAME_INVALID = "hotel.name.must.not.be.null-or-empty";
  public static final String HOTEL_DESCRIPTION_INVALID = "hotel.description.must.not.be.null-or-empty";
  public static final String HOTEL_STREET_INVALID = "hotel.street.must.not.be.null-or-empty";
  public static final String HOTEL_CEP_INVALID = "hotel.cep.invalid";
  public static final String HOTEL_CATEGORY_NOT_FOUND = "hotel.category.not.found";
  public static final String HOTEL_LOCALITY_NOT_FOUND = "hotel.locality.not.found";

  private ApplicationMessage() {}

}
