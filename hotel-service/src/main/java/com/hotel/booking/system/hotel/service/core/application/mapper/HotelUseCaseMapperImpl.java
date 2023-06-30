package com.hotel.booking.system.hotel.service.core.application.mapper;

import com.hotel.booking.system.hotel.service.core.application.dto.*;
import com.hotel.booking.system.hotel.service.core.domain.entity.Hotel;
import com.hotel.booking.system.hotel.service.core.domain.entity.Room;
import com.hotel.booking.system.hotel.service.core.domain.entity.Rooms;
import com.hotel.booking.system.hotel.service.core.domain.valueobject.HotelAddress;
import com.hotel.booking.system.hotel.service.core.domain.valueobject.HotelCategoryId;
import com.hotel.booking.system.hotel.service.core.domain.valueobject.LocalityId;
import com.hotel.booking.system.hotel.service.core.domain.valueobject.Money;
import com.hotel.booking.system.hotel.service.core.ports.api.mapper.HotelUseCaseMapper;
import com.hotel.booking.system.hotel.service.core.ports.spi.queries.SearchHotelAvailableQueryResult;

import java.util.stream.Collectors;

public class HotelUseCaseMapperImpl implements HotelUseCaseMapper {

  private static Room registerHotelRoomInputToRoom(final RegisterHotelRoomInput input) {
    return Room.builder()
      .id(null)
      .hotelId(null)
      .name(input.name())
      .description(input.description())
      .currentPrice(Money.of(input.currentPrice()))
      .capacity(input.capacity())
      .quantity(input.quantity())
      .build();
  }

  @Override
  public Hotel registerHotelInputToHotel(final RegisterHotelInput input) {
    final var address = HotelAddress.builder()
      .street(input.street())
      .cep(input.cep())
      .build();
    return Hotel.builder()
      .id(null)
      .name(input.name())
      .description(input.description())
      .address(address)
      .categoryId(HotelCategoryId.of(input.categoryId()))
      .localityId(LocalityId.of(input.localityId()))
      .rooms(
        Rooms.newInstance(
          input.rooms().stream()
            .map(HotelUseCaseMapperImpl::registerHotelRoomInputToRoom)
            .collect(Collectors.toList())
        ))
      .build();
  }

  @Override
  public RegisterHotelOutput hotelToRegisterHotelOutput(final Hotel hotel) {
    return new RegisterHotelOutput(hotel.getId().toString(), hotel.getRoom().asRawIds());
  }

  @Override
  public SearchHotelAvailableOutput searchHotelAvailableQueryResultToSearchHotelAvailableOutput(
    final SearchHotelAvailableQueryResult queryResult
  ) {
    return SearchHotelAvailableOutput.builder()
      .id(queryResult.getHotelId().toString())
      .name(queryResult.getHotelName())
      .description(queryResult.getHotelDescription())
      .address(queryResult.getHotelStreet() + " - " + queryResult.getHotelCep())
      .category(queryResult.getHotelCategoryName())
      .city(queryResult.getHotelCity())
      .state(queryResult.getHotelState())
      .country(queryResult.getHotelCountry())
      .rooms(
        queryResult.getRooms().stream()
          .map(roomQueryResult -> SearchHotelAvailableRoomOutput.builder()
            .id(roomQueryResult.getRoomId().toString())
            .name(roomQueryResult.getRoomName())
            .description(roomQueryResult.getRoomDescription())
            .currentPrice(roomQueryResult.getRoomCurrentPrice().getValue())
            .quantity(roomQueryResult.getRoomQuantity())
            .capacity(roomQueryResult.getRoomCapacity())
            .build()
          )
          .toList()
      )
      .build();
  }

}
