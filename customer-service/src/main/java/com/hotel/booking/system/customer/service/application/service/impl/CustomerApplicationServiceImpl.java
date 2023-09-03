package com.hotel.booking.system.customer.service.application.service.impl;

import com.hotel.booking.system.customer.service.application.service.CustomerApplicationService;
import com.hotel.booking.system.customer.service.core.application.dto.ReservationOrderDetailInput;
import com.hotel.booking.system.customer.service.core.application.dto.ReservationOrderDetailOutput;
import com.hotel.booking.system.customer.service.core.ports.api.usecase.GetCustomerReservationOrderDetail;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerApplicationServiceImpl implements CustomerApplicationService {

  private final GetCustomerReservationOrderDetail getCustomerReservationOrderDetail;

  @Override
  public ReservationOrderDetailOutput getCustomerReservationOrderDetail(final String customerId, final String reservationOrderId) {
    return this.getCustomerReservationOrderDetail.execute(new ReservationOrderDetailInput(customerId, reservationOrderId));
  }

}
