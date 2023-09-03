package com.hotel.booking.system.customer.service.core.application.usecase;

import com.hotel.booking.system.commons.core.domain.valueobject.CustomerId;
import com.hotel.booking.system.commons.core.domain.valueobject.ReservationOrderId;
import com.hotel.booking.system.customer.service.core.application.dto.ReservationOrderDetailInput;
import com.hotel.booking.system.customer.service.core.application.dto.ReservationOrderDetailOutput;
import com.hotel.booking.system.customer.service.core.ports.api.mapper.CustomerUseCaseMapper;
import com.hotel.booking.system.customer.service.core.ports.api.usecase.GetCustomerReservationOrderDetail;
import com.hotel.booking.system.customer.service.core.ports.spi.repository.CustomerRepository;
import com.hotel.booking.system.customer.service.core.ports.spi.repository.ReservationOrderRepository;

public class GetCustomerReservationOrderDetailImpl implements GetCustomerReservationOrderDetail {

  private final CustomerRepository customerRepository;
  private final ReservationOrderRepository reservationOrderRepository;
  private final CustomerUseCaseMapper customerUseCaseMapper;

  public GetCustomerReservationOrderDetailImpl(
    final CustomerRepository customerRepository,
    final ReservationOrderRepository reservationOrderRepository,
    final CustomerUseCaseMapper customerUseCaseMapper
  ) {
    this.customerRepository = customerRepository;
    this.reservationOrderRepository = reservationOrderRepository;
    this.customerUseCaseMapper = customerUseCaseMapper;
  }

  @Override
  public ReservationOrderDetailOutput execute(final ReservationOrderDetailInput input) {
    final var customer = this.customerRepository.findById(CustomerId.of(input.customerId()));
    final var reservationOrder = this.reservationOrderRepository.findById(ReservationOrderId.of(input.reservationOrderId()));
    return this.customerUseCaseMapper.reservationOrderToReservationOrderDetailOutput(reservationOrder, customer);
  }
}
