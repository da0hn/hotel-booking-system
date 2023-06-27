package com.hotel.booking.system.commons.application.dto;

import java.time.LocalDateTime;

public interface ErrorResponse<T> extends Response<T> {

  String getMessage();

  LocalDateTime getTime();

}
