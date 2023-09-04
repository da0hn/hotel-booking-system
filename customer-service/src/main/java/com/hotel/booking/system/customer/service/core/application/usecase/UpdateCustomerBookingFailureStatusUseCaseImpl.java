package com.hotel.booking.system.customer.service.core.application.usecase;

import com.hotel.booking.system.commons.core.domain.valueobject.ReservationOrderId;
import com.hotel.booking.system.customer.service.core.application.dto.UpdateCustomerBookingFailureStatusInput;
import com.hotel.booking.system.customer.service.core.ports.api.usecase.UpdateCustomerBookingFailureStatusUseCase;
import com.hotel.booking.system.customer.service.core.ports.spi.repository.ReservationOrderRepository;

public class UpdateCustomerBookingFailureStatusUseCaseImpl implements UpdateCustomerBookingFailureStatusUseCase {

  private final ReservationOrderRepository repository;

  public UpdateCustomerBookingFailureStatusUseCaseImpl(final ReservationOrderRepository repository) {
    this.repository = repository;
  }

  @Override
  public void execute(final UpdateCustomerBookingFailureStatusInput input) {
    final var reservationOrder = this.repository.findById(ReservationOrderId.of(input.reservationOrderId()));
    reservationOrder.updateToFailureStatus(input.status(), input.failureMessages());
    this.repository.save(reservationOrder);
  }
}
