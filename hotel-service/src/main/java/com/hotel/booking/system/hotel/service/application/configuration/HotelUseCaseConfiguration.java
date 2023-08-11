package com.hotel.booking.system.hotel.service.application.configuration;

import com.hotel.booking.system.hotel.service.core.application.mapper.HotelUseCaseMapperImpl;
import com.hotel.booking.system.hotel.service.core.application.usecase.BookingRoomRequestUseCaseImpl;
import com.hotel.booking.system.hotel.service.core.application.usecase.RegisterHotelUseCaseImpl;
import com.hotel.booking.system.hotel.service.core.application.usecase.SearchHotelAvailableUseCaseImpl;
import com.hotel.booking.system.hotel.service.core.ports.api.mapper.HotelUseCaseMapper;
import com.hotel.booking.system.hotel.service.core.ports.api.usecase.BookingRoomRequestUseCase;
import com.hotel.booking.system.hotel.service.core.ports.api.usecase.RegisterHotelUseCase;
import com.hotel.booking.system.hotel.service.core.ports.api.usecase.SearchHotelAvailableUseCase;
import com.hotel.booking.system.hotel.service.core.ports.spi.messaging.publisher.BookingRoomRequestedPublisher;
import com.hotel.booking.system.hotel.service.core.ports.spi.messaging.publisher.CustomerBookingRoomUpdatePublisher;
import com.hotel.booking.system.hotel.service.core.ports.spi.repository.HotelRepository;
import com.hotel.booking.system.hotel.service.core.ports.spi.repository.LocalityRepository;
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
  public SearchHotelAvailableUseCase searchHotelAvailableUseCase(
    final HotelRepository hotelRepository,
    final HotelUseCaseMapper hotelUseCaseMapper
  ) {
    return new SearchHotelAvailableUseCaseImpl(hotelRepository, hotelUseCaseMapper);
  }

  @Bean
  public HotelUseCaseMapper hotelUseCaseMapper() {
    return new HotelUseCaseMapperImpl();
  }

  @Bean
  public BookingRoomRequestUseCase bookingRoomRequestedUseCase(
    final HotelRepository hotelRepository,
    final CustomerBookingRoomUpdatePublisher customerBookingRoomUpdatePublisher,
    final BookingRoomRequestedPublisher bookingRoomRequestedPublisher
  ) {
    return new BookingRoomRequestUseCaseImpl(
      hotelRepository,
      customerBookingRoomUpdatePublisher,
      bookingRoomRequestedPublisher
    );
  }

}
