package com.hotel.booking.system.hotel.service.core.ports.api.mapper;

import com.hotel.booking.system.hotel.service.core.application.dto.RegisterHotelInput;
import com.hotel.booking.system.hotel.service.core.application.dto.RegisterHotelOutput;
import com.hotel.booking.system.hotel.service.core.application.dto.SearchHotelAvailableOutput;
import com.hotel.booking.system.hotel.service.core.domain.entity.Hotel;
import com.hotel.booking.system.hotel.service.core.ports.spi.queries.SearchHotelAvailableQueryResult;

public interface HotelUseCaseMapper {
  Hotel registerHotelInputToHotel(RegisterHotelInput input);

  RegisterHotelOutput hotelToRegisterHotelOutput(Hotel hotel);

  SearchHotelAvailableOutput searchHotelAvailableQueryResultToSearchHotelAvailableOutput(
    SearchHotelAvailableQueryResult queryResult
  );
}
