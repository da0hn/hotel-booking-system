package com.hotel.booking.system.hotel.service.application.service;

import com.hotel.booking.system.hotel.service.core.application.dto.RegisterHotelInput;
import com.hotel.booking.system.hotel.service.core.application.dto.RegisterHotelOutput;

public interface HotelApplicationService {


  RegisterHotelOutput register(RegisterHotelInput input);


}
