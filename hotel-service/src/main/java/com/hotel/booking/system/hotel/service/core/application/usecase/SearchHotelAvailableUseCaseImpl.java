package com.hotel.booking.system.hotel.service.core.application.usecase;

import com.hotel.booking.system.hotel.service.core.application.dto.SearchHotelAvailableInput;
import com.hotel.booking.system.hotel.service.core.application.dto.SearchHotelAvailableOutput;
import com.hotel.booking.system.hotel.service.core.ports.api.mapper.HotelUseCaseMapper;
import com.hotel.booking.system.hotel.service.core.ports.api.usecase.SearchHotelAvailableUseCase;
import com.hotel.booking.system.hotel.service.core.ports.spi.repository.HotelRepository;

import java.util.List;
import java.util.stream.Collectors;

public class SearchHotelAvailableUseCaseImpl implements SearchHotelAvailableUseCase {

  private final HotelRepository hotelRepository;
  private final HotelUseCaseMapper hotelUseCaseMapper;

  public SearchHotelAvailableUseCaseImpl(
    final HotelRepository hotelRepository,
    final HotelUseCaseMapper hotelUseCaseMapper
  ) {
    this.hotelRepository = hotelRepository;
    this.hotelUseCaseMapper = hotelUseCaseMapper;
  }

  @Override
  public List<SearchHotelAvailableOutput> execute(final SearchHotelAvailableInput input) {
    final var queryResult = this.hotelRepository.searchHotelAvailableBy(
      input.name(),
      input.category(),
      input.city(),
      input.state()
    );

    return queryResult.stream()
      .map(this.hotelUseCaseMapper::searchHotelAvailableQueryResultToSearchHotelAvailableOutput)
      .collect(Collectors.toList());
  }

}
