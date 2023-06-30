package com.hotel.booking.system.hotel.service.core.ports.api.usecase;

import com.hotel.booking.system.hotel.service.core.application.dto.SearchHotelAvailableInput;
import com.hotel.booking.system.hotel.service.core.application.dto.SearchHotelAvailableOutput;

import java.util.List;

@FunctionalInterface
public interface SearchHotelAvailableUseCase {

  List<SearchHotelAvailableOutput> execute(SearchHotelAvailableInput input);

}
