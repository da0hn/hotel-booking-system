package com.hotel.booking.system.hotel.service.core.ports.api.usecase;

import com.hotel.booking.system.hotel.service.core.application.dto.RegisterHotelInput;
import com.hotel.booking.system.hotel.service.core.application.dto.RegisterHotelOutput;

@FunctionalInterface
public interface RegisterHotelUseCase {

  RegisterHotelOutput execute(RegisterHotelInput input);


}
