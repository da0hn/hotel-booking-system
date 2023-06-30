package com.hotel.booking.system.hotel.service.application.service;

import com.hotel.booking.system.hotel.service.core.application.dto.RegisterHotelInput;
import com.hotel.booking.system.hotel.service.core.application.dto.RegisterHotelOutput;
import com.hotel.booking.system.hotel.service.core.application.dto.SearchHotelAvailableInput;
import com.hotel.booking.system.hotel.service.core.application.dto.SearchHotelAvailableOutput;

import java.util.List;

public interface HotelApplicationService {


  RegisterHotelOutput register(RegisterHotelInput input);


  List<SearchHotelAvailableOutput> searchHotelAvailableBy(SearchHotelAvailableInput input);
}
