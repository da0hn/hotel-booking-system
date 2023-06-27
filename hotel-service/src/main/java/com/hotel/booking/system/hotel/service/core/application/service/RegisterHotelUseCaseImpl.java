package com.hotel.booking.system.hotel.service.core.application.service;

import com.hotel.booking.system.hotel.service.core.application.dto.RegisterHotelInput;
import com.hotel.booking.system.hotel.service.core.application.dto.RegisterHotelOutput;
import com.hotel.booking.system.hotel.service.core.domain.entity.Hotel;
import com.hotel.booking.system.hotel.service.core.domain.exception.HotelDomainException;
import com.hotel.booking.system.hotel.service.core.ports.api.HotelUseCaseMapper;
import com.hotel.booking.system.hotel.service.core.ports.api.RegisterHotelUseCase;
import com.hotel.booking.system.hotel.service.core.ports.spi.HotelRepository;
import com.hotel.booking.system.hotel.service.core.ports.spi.LocalityRepository;
import com.hotel.booking.system.hotel.service.core.shared.HotelDomainMessage;

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
    hotel.validate();
    hotel.initialize();
    this.hotelRepository.register(hotel);
    return this.hotelUseCaseMapper.hotelToRegisterHotelOutput(hotel);
  }

  private void validateCategoryId(final Hotel hotel) {
    if (!this.hotelRepository.existsCategoryById(hotel.getCategoryId())) {
      throw new HotelDomainException(HotelDomainMessage.HOTEL_CATEGORY_NOT_FOUND);
    }
  }

  private void validateLocality(final Hotel hotel) {
    if (!this.localityRepository.existsLocalityById(hotel.getLocalityId())) {
      throw new HotelDomainException(HotelDomainMessage.HOTEL_LOCALITY_NOT_FOUND);
    }
  }
}
