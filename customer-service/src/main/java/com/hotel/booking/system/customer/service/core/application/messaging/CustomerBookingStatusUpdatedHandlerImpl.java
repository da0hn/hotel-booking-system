package com.hotel.booking.system.customer.service.core.application.messaging;

import com.hotel.booking.system.commons.core.domain.event.customer.CustomerBookingInitiatedEvent;
import com.hotel.booking.system.commons.core.domain.event.customer.CustomerBookingPaymentCompletedEvent;
import com.hotel.booking.system.commons.core.domain.event.customer.CustomerBookingPaymentFailedEvent;
import com.hotel.booking.system.commons.core.domain.event.customer.CustomerBookingPaymentRequestedEvent;
import com.hotel.booking.system.commons.core.domain.event.customer.CustomerBookingRejectedEvent;
import com.hotel.booking.system.commons.core.domain.event.customer.CustomerBookingStatusUpdatedEvent;
import com.hotel.booking.system.customer.service.core.ports.api.mapper.CustomerUseCaseMapper;
import com.hotel.booking.system.customer.service.core.ports.api.messaging.CustomerBookingStatusUpdatedHandler;
import com.hotel.booking.system.customer.service.core.ports.api.usecase.InitializeCustomerBookingUseCase;

public class CustomerBookingStatusUpdatedHandlerImpl implements CustomerBookingStatusUpdatedHandler {

  private final InitializeCustomerBookingUseCase initializeCustomerBookingUseCase;
  private final CustomerUseCaseMapper customerUseCaseMapper;

  public CustomerBookingStatusUpdatedHandlerImpl(
    final InitializeCustomerBookingUseCase initializeCustomerBookingUseCase,
    final CustomerUseCaseMapper customerUseCaseMapper
  ) {
    this.initializeCustomerBookingUseCase = initializeCustomerBookingUseCase;
    this.customerUseCaseMapper = customerUseCaseMapper;
  }

  @Override
  public void handle(final CustomerBookingStatusUpdatedEvent event) {
    switch (event) {
      case final CustomerBookingInitiatedEvent e -> {
        final var input = this.customerUseCaseMapper.customerBookingInitiatedEventToInitializeCustomerBookingInput(e);
        this.initializeCustomerBookingUseCase.execute(input);
      }
      case final CustomerBookingPaymentRequestedEvent e -> {
      }
      case final CustomerBookingPaymentCompletedEvent e -> {
      }
      case final CustomerBookingPaymentFailedEvent e -> {
      }
      case final CustomerBookingRejectedEvent e -> {
      }
    }
  }
}
