package com.hotel.booking.system.hotel.service.application.service.impl;

import com.hotel.booking.system.hotel.service.application.service.HotelApplicationService;
import com.hotel.booking.system.hotel.service.core.application.dto.RegisterHotelInput;
import com.hotel.booking.system.hotel.service.core.application.dto.RegisterHotelOutput;
import com.hotel.booking.system.hotel.service.core.application.dto.SearchHotelAvailableInput;
import com.hotel.booking.system.hotel.service.core.application.dto.SearchHotelAvailableOutput;
import com.hotel.booking.system.hotel.service.core.ports.api.usecase.RegisterHotelUseCase;
import com.hotel.booking.system.hotel.service.core.ports.api.usecase.SearchHotelAvailableUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class HotelApplicationServiceImpl implements HotelApplicationService {

  private final RegisterHotelUseCase registerHotelUseCase;
  private final SearchHotelAvailableUseCase searchHotelAvailableUseCase;

  @Override
  public RegisterHotelOutput register(final RegisterHotelInput input) {
    return this.registerHotelUseCase.execute(input);
  }

  @Override
  public List<SearchHotelAvailableOutput> searchHotelAvailableBy(final SearchHotelAvailableInput input) {
    return this.searchHotelAvailableUseCase.execute(input);
  }
}
