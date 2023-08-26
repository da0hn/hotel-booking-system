package com.hotel.booking.system.booking.service.application.configuration;

import com.hotel.booking.system.booking.service.core.application.mapper.BookingUseCaseMapperImpl;
import com.hotel.booking.system.booking.service.core.application.messaging.BookingRoomRequestedHandlerImpl;
import com.hotel.booking.system.booking.service.core.application.messaging.BookingRoomStatusChangedHandlerImpl;
import com.hotel.booking.system.booking.service.core.application.service.BookingInitializer;
import com.hotel.booking.system.booking.service.core.application.usecase.BookingRoomUseCaseImpl;
import com.hotel.booking.system.booking.service.core.application.usecase.UpdateBookingStatusUseCaseImpl;
import com.hotel.booking.system.booking.service.core.ports.api.mapper.BookingUseCaseMapper;
import com.hotel.booking.system.booking.service.core.ports.api.messaging.BookingRoomRequestedHandler;
import com.hotel.booking.system.booking.service.core.ports.api.messaging.BookingRoomStatusChangedHandler;
import com.hotel.booking.system.booking.service.core.ports.api.usecase.BookingRoomUseCase;
import com.hotel.booking.system.booking.service.core.ports.api.usecase.UpdateBookingStatusUseCase;
import com.hotel.booking.system.booking.service.core.ports.spi.messaging.publisher.BookingRoomResponsePublisher;
import com.hotel.booking.system.booking.service.core.ports.spi.repository.BookingRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookingBeanConfiguration {

  @Bean
  public BookingRoomRequestedHandler bookingRoomRequestedHandler(
    final BookingRoomUseCase bookingRoomUseCase,
    final BookingUseCaseMapper bookingUseCaseMapper,
    final BookingRoomResponsePublisher bookingRoomResponsePublisher
  ) {
    return new BookingRoomRequestedHandlerImpl(
      bookingRoomUseCase,
      bookingUseCaseMapper,
      bookingRoomResponsePublisher
    );
  }

  @Bean
  public BookingRoomUseCase bookingRoomUseCase(
    final BookingRepository bookingRepository,
    final BookingUseCaseMapper bookingUseCaseMapper,
    final BookingInitializer bookingInitializer
  ) {
    return new BookingRoomUseCaseImpl(
      bookingRepository,
      bookingUseCaseMapper,
      bookingInitializer
    );
  }

  @Bean
  public BookingInitializer bookingInitializer() {
    return new BookingInitializer();
  }

  @Bean
  public BookingUseCaseMapper bookingRoomUseCaseMapper() {
    return new BookingUseCaseMapperImpl();
  }

  @Bean
  public BookingRoomStatusChangedHandler bookingRoomStatusChangedHandler(
    final BookingUseCaseMapper bookingUseCaseMapper,
    final UpdateBookingStatusUseCase updateBookingRoomStatusUseCase
  ) {
    return new BookingRoomStatusChangedHandlerImpl(bookingUseCaseMapper, updateBookingRoomStatusUseCase);
  }

  @Bean
  public UpdateBookingStatusUseCase updateBookingRoomStatusUseCase(final BookingRepository bookingRepository) {
    return new UpdateBookingStatusUseCaseImpl(bookingRepository);
  }

}
