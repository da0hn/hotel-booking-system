package com.hotel.booking.system.customer.service.core.ports.api.usecase;

import com.hotel.booking.system.customer.service.core.application.dto.ReservationOrderDetailInput;
import com.hotel.booking.system.customer.service.core.application.dto.ReservationOrderDetailOutput;

public interface GetCustomerReservationOrderDetail {

  ReservationOrderDetailOutput execute(ReservationOrderDetailInput input);

}
