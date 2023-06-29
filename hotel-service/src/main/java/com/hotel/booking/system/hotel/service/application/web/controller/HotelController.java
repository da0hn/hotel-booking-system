package com.hotel.booking.system.hotel.service.application.web.controller;

import com.hotel.booking.system.commons.application.dto.Response;
import com.hotel.booking.system.commons.application.dto.impl.ResponseEntityAdapter;
import com.hotel.booking.system.hotel.service.application.service.HotelApplicationService;
import com.hotel.booking.system.hotel.service.core.application.dto.RegisterHotelInput;
import com.hotel.booking.system.hotel.service.core.application.dto.RegisterHotelOutput;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/hotel")
public class HotelController {

  private final HotelApplicationService hotelApplicationService;

  @PostMapping
  public ResponseEntity<Response<RegisterHotelOutput>> register(@RequestBody final RegisterHotelInput input) {
    final var output = this.hotelApplicationService.register(input);
    return ResponseEntityAdapter.of(output);
  }

}
