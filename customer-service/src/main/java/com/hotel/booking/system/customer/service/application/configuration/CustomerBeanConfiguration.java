package com.hotel.booking.system.customer.service.application.configuration;

import com.hotel.booking.system.customer.service.core.application.mapper.CustomerUseCaseMapperImpl;
import com.hotel.booking.system.customer.service.core.application.messaging.CustomerBookingStatusUpdatedHandlerImpl;
import com.hotel.booking.system.customer.service.core.application.usecase.InitializeCustomerBookingUseCaseImpl;
import com.hotel.booking.system.customer.service.core.application.usecase.UpdateCustomerBookingStatusUseCaseImpl;
import com.hotel.booking.system.customer.service.core.ports.api.mapper.CustomerUseCaseMapper;
import com.hotel.booking.system.customer.service.core.ports.api.messaging.CustomerBookingStatusUpdatedHandler;
import com.hotel.booking.system.customer.service.core.ports.api.usecase.InitializeCustomerBookingUseCase;
import com.hotel.booking.system.customer.service.core.ports.api.usecase.UpdateCustomerBookingStatusUseCase;
import com.hotel.booking.system.customer.service.core.ports.spi.repository.CustomerRepository;
import com.hotel.booking.system.customer.service.core.ports.spi.repository.ReservationOrderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerBeanConfiguration {

  @Bean
  public CustomerUseCaseMapper customerUseCaseMapper() {
    return new CustomerUseCaseMapperImpl();
  }

  @Bean
  public InitializeCustomerBookingUseCase initiateCustomerBookingUseCase(
    final CustomerRepository customerRepository,
    final ReservationOrderRepository reservationOrderRepository,
    final CustomerUseCaseMapper customerUseCaseMapper
  ) {
    return new InitializeCustomerBookingUseCaseImpl(customerRepository, reservationOrderRepository, customerUseCaseMapper);
  }

  @Bean
  public CustomerBookingStatusUpdatedHandler customerBookingStatusUpdatedHandler(
    final InitializeCustomerBookingUseCase initializeCustomerBookingUseCase,
    final UpdateCustomerBookingStatusUseCase updateCustomerBookingStatusUseCase,
    final CustomerUseCaseMapper customerUseCaseMapper
  ) {
    return new CustomerBookingStatusUpdatedHandlerImpl(
      initializeCustomerBookingUseCase,
      updateCustomerBookingStatusUseCase,
      customerUseCaseMapper
    );
  }

  @Bean
  public UpdateCustomerBookingStatusUseCase updateCustomerBookingStatusUseCase(
    final ReservationOrderRepository reservationOrderRepository
  ) {
    return new UpdateCustomerBookingStatusUseCaseImpl(reservationOrderRepository);
  }

}
