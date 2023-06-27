package com.hotel.booking.system.commons.application.dto;

public interface Response<T> {

  T getData();

  boolean getSuccess();

}
