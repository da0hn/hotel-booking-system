package com.hotel.booking.system.hotel.service.core.application.usecase;

import com.hotel.booking.system.commons.core.message.ApplicationMessage;
import com.hotel.booking.system.hotel.service.core.application.dto.RegisterHotelInput;
import com.hotel.booking.system.hotel.service.core.application.dto.RegisterHotelOutput;
import com.hotel.booking.system.hotel.service.core.domain.entity.Hotel;
import com.hotel.booking.system.hotel.service.core.domain.exception.HotelDomainException;
import com.hotel.booking.system.hotel.service.core.ports.api.mapper.HotelUseCaseMapper;
import com.hotel.booking.system.hotel.service.core.ports.api.usecase.RegisterHotelUseCase;
import com.hotel.booking.system.hotel.service.core.ports.spi.repository.HotelRepository;
import com.hotel.booking.system.hotel.service.core.ports.spi.repository.LocalityRepository;

public class RegisterHotelUseCaseImpl implements RegisterHotelUseCase {

  private final HotelRepository hotelRepository;
  private final LocalityRepository localityRepository;
  private final HotelUseCaseMapper hotelUseCaseMapper;

  public RegisterHotelUseCaseImpl(
    final HotelRepository hotelRepository,
    final LocalityRepository localityRepository,
    final HotelUseCaseMapper hotelUseCaseMapper
  ) {
    this.hotelRepository = hotelRepository;
    this.localityRepository = localityRepository;
    this.hotelUseCaseMapper = hotelUseCaseMapper;
  }

  @Override
  public RegisterHotelOutput execute(final RegisterHotelInput input) {
    final var hotel = this.hotelUseCaseMapper.registerHotelInputToHotel(input);
    this.validateCategoryId(hotel);
    this.validateLocality(hotel);
    hotel.initialize();
    hotel.validate();
    this.hotelRepository.register(hotel);
    return this.hotelUseCaseMapper.hotelToRegisterHotelOutput(hotel);
  }

  private void validateCategoryId(final Hotel hotel) {
    if (!this.hotelRepository.existsCategoryById(hotel.getCategoryId())) {
      throw new HotelDomainException(ApplicationMessage.HOTEL_CATEGORY_NOT_FOUND);
    }
  }

  private void validateLocality(final Hotel hotel) {
    if (!this.localityRepository.existsLocalityById(hotel.getLocalityId())) {
      throw new HotelDomainException(ApplicationMessage.HOTEL_LOCALITY_NOT_FOUND);
    }
  }
}
