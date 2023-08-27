package com.hotel.booking.system.customer.service.core.application.usecase;

import com.hotel.booking.system.commons.core.domain.valueobject.ReservationOrderId;
import com.hotel.booking.system.customer.service.core.application.dto.UpdateCustomerBookingStatusInput;
import com.hotel.booking.system.customer.service.core.ports.api.usecase.UpdateCustomerBookingStatusUseCase;
import com.hotel.booking.system.customer.service.core.ports.spi.repository.ReservationOrderRepository;

public class UpdateCustomerBookingStatusUseCaseImpl implements UpdateCustomerBookingStatusUseCase {

  private final ReservationOrderRepository repository;

  public UpdateCustomerBookingStatusUseCaseImpl(final ReservationOrderRepository repository) {
    this.repository = repository;
  }

  @Override
  public void execute(final UpdateCustomerBookingStatusInput input) {
    final var reservationOrder = this.repository.findById(ReservationOrderId.of(input.reservationOrderId()));
    reservationOrder.updateStatus(input.status());
    this.repository.save(reservationOrder);
  }
}
