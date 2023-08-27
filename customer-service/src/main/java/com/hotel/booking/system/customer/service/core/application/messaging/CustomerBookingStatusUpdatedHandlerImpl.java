package com.hotel.booking.system.customer.service.core.application.messaging;

import com.hotel.booking.system.commons.core.domain.event.customer.CustomerBookingInitiatedEvent;
import com.hotel.booking.system.commons.core.domain.event.customer.CustomerBookingStatusUpdatedEvent;
import com.hotel.booking.system.customer.service.core.ports.api.mapper.CustomerUseCaseMapper;
import com.hotel.booking.system.customer.service.core.ports.api.messaging.CustomerBookingStatusUpdatedHandler;
import com.hotel.booking.system.customer.service.core.ports.api.usecase.InitializeCustomerBookingUseCase;
import com.hotel.booking.system.customer.service.core.ports.api.usecase.UpdateCustomerBookingStatusUseCase;

public class CustomerBookingStatusUpdatedHandlerImpl implements CustomerBookingStatusUpdatedHandler {

  private final InitializeCustomerBookingUseCase initializeCustomerBookingUseCase;
  private final UpdateCustomerBookingStatusUseCase updateCustomerBookingStatusUseCase;
  private final CustomerUseCaseMapper customerUseCaseMapper;

  public CustomerBookingStatusUpdatedHandlerImpl(
    final InitializeCustomerBookingUseCase initializeCustomerBookingUseCase,
    final UpdateCustomerBookingStatusUseCase updateCustomerBookingStatusUseCase, final CustomerUseCaseMapper customerUseCaseMapper
  ) {
    this.initializeCustomerBookingUseCase = initializeCustomerBookingUseCase;
    this.updateCustomerBookingStatusUseCase = updateCustomerBookingStatusUseCase;
    this.customerUseCaseMapper = customerUseCaseMapper;
  }

  @Override
  public void handle(final CustomerBookingStatusUpdatedEvent event) {
    switch (event) {
      case final CustomerBookingInitiatedEvent e -> {
        final var input = this.customerUseCaseMapper.customerBookingInitiatedEventToInitializeCustomerBookingInput(e);
        this.initializeCustomerBookingUseCase.execute(input);
      }
      case final CustomerBookingStatusUpdatedEvent e -> {
        final var input = this.customerUseCaseMapper.customerBookingStatusUpdateEventToUpdateCustomerBookingStatusInput(e);
        this.updateCustomerBookingStatusUseCase.execute(input);
      }
    }
  }
}
