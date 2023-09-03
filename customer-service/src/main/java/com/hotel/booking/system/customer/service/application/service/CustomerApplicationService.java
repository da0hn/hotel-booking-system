package com.hotel.booking.system.customer.service.application.service;

import com.hotel.booking.system.customer.service.core.application.dto.ReservationOrderDetailOutput;

public interface CustomerApplicationService {
  ReservationOrderDetailOutput getCustomerReservationOrderDetail(String customerId, String reservationOrderId);
}
