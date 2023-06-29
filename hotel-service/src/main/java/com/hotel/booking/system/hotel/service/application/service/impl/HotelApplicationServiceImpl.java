package com.hotel.booking.system.hotel.service.application.service.impl;

import com.hotel.booking.system.hotel.service.application.service.HotelApplicationService;
import com.hotel.booking.system.hotel.service.core.application.dto.RegisterHotelInput;
import com.hotel.booking.system.hotel.service.core.application.dto.RegisterHotelOutput;
import com.hotel.booking.system.hotel.service.core.ports.api.RegisterHotelUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class HotelApplicationServiceImpl implements HotelApplicationService {

  private final RegisterHotelUseCase registerHotelUseCase;

  @Override
  public RegisterHotelOutput register(final RegisterHotelInput input) {
    return this.registerHotelUseCase.execute(input);
  }
}
