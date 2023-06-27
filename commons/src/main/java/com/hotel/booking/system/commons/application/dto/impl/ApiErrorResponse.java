package com.hotel.booking.system.commons.application.dto.impl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotel.booking.system.commons.application.dto.ErrorResponse;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class ApiErrorResponse<T> implements ErrorResponse<T> {

  private final T data;
  private final String message;
  @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  private final LocalDateTime time = LocalDateTime.now();

  public static <T> ErrorResponse<T> of(
    final T data,
    final String message
  ) {
    return new ApiErrorResponse<>(data, message);
  }

  public static ErrorResponse<Void> of(final String message) {
    return new ApiErrorResponse<>(null, message);
  }

  @Override
  public String getMessage() {
    return this.message;
  }

  @Override
  public LocalDateTime getTime() {
    return this.time;
  }

  @Override
  public T getData() {
    return this.data;
  }

  @Override
  public boolean getSuccess() {
    return false;
  }

  public record ApiErrorDetail(String detail) {}
}
