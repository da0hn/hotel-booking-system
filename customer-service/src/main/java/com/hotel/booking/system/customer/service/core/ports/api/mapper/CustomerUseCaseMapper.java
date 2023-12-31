package com.hotel.booking.system.customer.service.core.ports.api.mapper;

import com.hotel.booking.system.commons.core.domain.event.customer.CustomerBookingFailureStatusUpdateEvent;
import com.hotel.booking.system.commons.core.domain.event.customer.CustomerBookingInitiatedEvent;
import com.hotel.booking.system.commons.core.domain.event.customer.CustomerBookingStatusUpdatedEvent;
import com.hotel.booking.system.customer.service.core.application.dto.InitializeReservationOrderInput;
import com.hotel.booking.system.customer.service.core.application.dto.ReservationOrderDetailOutput;
import com.hotel.booking.system.customer.service.core.application.dto.UpdateCustomerBookingFailureStatusInput;
import com.hotel.booking.system.customer.service.core.application.dto.UpdateCustomerBookingStatusInput;
import com.hotel.booking.system.customer.service.core.domain.entity.Customer;
import com.hotel.booking.system.customer.service.core.domain.entity.ReservationOrder;

public interface CustomerUseCaseMapper {
  InitializeReservationOrderInput customerBookingInitiatedEventToInitializeCustomerBookingInput(CustomerBookingInitiatedEvent event);

  ReservationOrder initializeReservationOrderInputToReservationOrder(InitializeReservationOrderInput input);

  UpdateCustomerBookingStatusInput customerBookingStatusUpdateEventToUpdateCustomerBookingStatusInput(CustomerBookingStatusUpdatedEvent event);

  ReservationOrderDetailOutput reservationOrderToReservationOrderDetailOutput(ReservationOrder reservationOrder, Customer customer);

  UpdateCustomerBookingFailureStatusInput customerBookingFailureStatusUpdateEventToUpdateCustomerBookingFailureStatusInput(
    CustomerBookingFailureStatusUpdateEvent event
  );

}
