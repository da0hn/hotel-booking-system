package com.hotel.booking.system.customer.service.application.web.controller;

import com.hotel.booking.system.commons.application.dto.Response;
import com.hotel.booking.system.commons.application.dto.impl.ResponseEntityAdapter;
import com.hotel.booking.system.customer.service.application.service.CustomerApplicationService;
import com.hotel.booking.system.customer.service.core.application.dto.ReservationOrderDetailOutput;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

  private final CustomerApplicationService customerApplicationService;

  @GetMapping("/{customerId}/reservation-order/{reservationOrderId}")
  public ResponseEntity<Response<ReservationOrderDetailOutput>> getCustomerReservationOrderDetail(
    @PathVariable final String customerId,
    @PathVariable final String reservationOrderId
  ) {
    final var output = this.customerApplicationService.getCustomerReservationOrderDetail(
      customerId,
      reservationOrderId
    );
    return ResponseEntityAdapter.of(output);
  }


}
