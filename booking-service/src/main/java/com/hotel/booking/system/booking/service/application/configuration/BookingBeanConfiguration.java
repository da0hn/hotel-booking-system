package com.hotel.booking.system.booking.service.application.configuration;

import com.hotel.booking.system.booking.service.core.application.mapper.BookingUseCaseMapperImpl;
import com.hotel.booking.system.booking.service.core.application.messaging.BookingRoomRequestedHandlerImpl;
import com.hotel.booking.system.booking.service.core.application.usecase.BookingRoomUseCaseImpl;
import com.hotel.booking.system.booking.service.core.ports.api.mapper.BookingUseCaseMapper;
import com.hotel.booking.system.booking.service.core.ports.api.messaging.BookingRoomRequestedHandler;
import com.hotel.booking.system.booking.service.core.ports.api.usecase.BookingRoomUseCase;
import com.hotel.booking.system.booking.service.core.ports.spi.repository.BookingRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookingBeanConfiguration {

  @Bean
  public BookingRoomRequestedHandler bookingRoomRequestedHandler(
    final BookingRoomUseCase bookingRoomUseCase,
    final BookingUseCaseMapper bookingUseCaseMapper
  ) {
    return new BookingRoomRequestedHandlerImpl(
      bookingRoomUseCase,
      bookingUseCaseMapper
    );
  }

  @Bean
  public BookingRoomUseCase bookingRoomUseCase(
    final BookingRepository bookingRepository,
    final BookingUseCaseMapper bookingUseCaseMapper
  ) {
    return new BookingRoomUseCaseImpl(
      bookingRepository,
      bookingUseCaseMapper
    );
  }

  @Bean
  public BookingUseCaseMapper bookingRoomUseCaseMapper() {
    return new BookingUseCaseMapperImpl();
  }

}
