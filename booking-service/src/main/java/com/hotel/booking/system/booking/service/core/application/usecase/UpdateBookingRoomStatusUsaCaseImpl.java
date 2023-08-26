package com.hotel.booking.system.booking.service.core.application.usecase;

import com.hotel.booking.system.booking.service.core.application.dto.UpdateBookingRoomStatusInput;
import com.hotel.booking.system.booking.service.core.application.dto.UpdateBookingRoomStatusOutput;
import com.hotel.booking.system.booking.service.core.ports.api.usecase.UpdateBookingRoomStatusUseCase;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UpdateBookingRoomStatusUsaCaseImpl implements UpdateBookingRoomStatusUseCase {
  @Override
  public UpdateBookingRoomStatusOutput execute(final UpdateBookingRoomStatusInput input) {
    return new UpdateBookingRoomStatusOutput();
  }
}
