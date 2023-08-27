package com.hotel.booking.system.customer.service.core.application.usecase;

import com.hotel.booking.system.customer.service.core.application.dto.InitializeReservationOrderInput;
import com.hotel.booking.system.customer.service.core.domain.entity.ReservationOrder;
import com.hotel.booking.system.customer.service.core.domain.exception.CustomerNotFoundException;
import com.hotel.booking.system.customer.service.core.ports.api.mapper.CustomerUseCaseMapper;
import com.hotel.booking.system.customer.service.core.ports.api.usecase.InitializeCustomerBookingUseCase;
import com.hotel.booking.system.customer.service.core.ports.spi.repository.CustomerRepository;
import com.hotel.booking.system.customer.service.core.ports.spi.repository.ReservationOrderRepository;

public class InitializeCustomerBookingUseCaseImpl implements InitializeCustomerBookingUseCase {

  private final CustomerRepository customerRepository;
  private final ReservationOrderRepository reservationOrderRepository;
  private final CustomerUseCaseMapper customerUseCaseMapper;

  public InitializeCustomerBookingUseCaseImpl(
    final CustomerRepository customerRepository,
    final ReservationOrderRepository reservationOrderRepository,
    final CustomerUseCaseMapper customerUseCaseMapper
  ) {
    this.customerRepository = customerRepository;
    this.reservationOrderRepository = reservationOrderRepository;
    this.customerUseCaseMapper = customerUseCaseMapper;
  }

  @Override
  public void execute(final InitializeReservationOrderInput input) {
    final var reservationOrder = this.customerUseCaseMapper.initializeReservationOrderInputToReservationOrder(input);
    reservationOrder.initialize();
    this.ensureCustomerExists(reservationOrder);
    this.reservationOrderRepository.save(reservationOrder);
  }

  private void ensureCustomerExists(final ReservationOrder reservationOrder) {
    if (!this.customerRepository.customerExistsBy(reservationOrder.getCustomerId())) {
      throw new CustomerNotFoundException();
    }
  }

}
