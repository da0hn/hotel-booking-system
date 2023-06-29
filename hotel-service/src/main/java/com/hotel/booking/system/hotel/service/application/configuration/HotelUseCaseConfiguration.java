package com.hotel.booking.system.hotel.service.application.configuration;

import com.hotel.booking.system.hotel.service.core.application.mapper.HotelUseCaseMapperImpl;
import com.hotel.booking.system.hotel.service.core.application.usecase.RegisterHotelUseCaseImpl;
import com.hotel.booking.system.hotel.service.core.ports.api.HotelUseCaseMapper;
import com.hotel.booking.system.hotel.service.core.ports.api.RegisterHotelUseCase;
import com.hotel.booking.system.hotel.service.core.ports.spi.HotelRepository;
import com.hotel.booking.system.hotel.service.core.ports.spi.LocalityRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HotelUseCaseConfiguration {

  @Bean
  public RegisterHotelUseCase registerHotelUseCase(
    final HotelRepository hotelRepository,
    final LocalityRepository localityRepository,
    final HotelUseCaseMapper hotelUseCaseMapper
  ) {
    return new RegisterHotelUseCaseImpl(hotelRepository, localityRepository, hotelUseCaseMapper);
  }

  @Bean
  public HotelUseCaseMapper hotelUseCaseMapper() {
    return new HotelUseCaseMapperImpl();
  }
}
