package com.hotel.booking.system.hotel.service.application.web.controller;

import com.hotel.booking.system.commons.application.dto.CollectionResponse;
import com.hotel.booking.system.commons.application.dto.Response;
import com.hotel.booking.system.commons.application.dto.impl.ResponseEntityAdapter;
import com.hotel.booking.system.hotel.service.application.service.HotelApplicationService;
import com.hotel.booking.system.hotel.service.core.application.dto.RegisterHotelInput;
import com.hotel.booking.system.hotel.service.core.application.dto.RegisterHotelOutput;
import com.hotel.booking.system.hotel.service.core.application.dto.SearchHotelAvailableInput;
import com.hotel.booking.system.hotel.service.core.application.dto.SearchHotelAvailableOutput;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

  @GetMapping
  public ResponseEntity<CollectionResponse<SearchHotelAvailableOutput>> searchHotelAvailableBy(
    @RequestParam(value = "name", required = false, defaultValue = "") final String name,
    @RequestParam(value = "city", required = false, defaultValue = "") final String city,
    @RequestParam(value = "state", required = false, defaultValue = "") final String state,
    @RequestParam(value = "category", required = false, defaultValue = "") final String category
  ) {
    final List<SearchHotelAvailableOutput> output = this.hotelApplicationService.searchHotelAvailableBy(
      SearchHotelAvailableInput.builder()
        .name(name)
        .category(category)
        .city(city)
        .state(state)
        .build()
    );
    return ResponseEntityAdapter.items(output);
  }
}
