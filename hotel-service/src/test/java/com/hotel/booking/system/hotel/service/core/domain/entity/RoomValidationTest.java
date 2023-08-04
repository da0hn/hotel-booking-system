package com.hotel.booking.system.hotel.service.core.domain.entity;

import com.hotel.booking.system.commons.core.domain.valueobject.HotelId;
import com.hotel.booking.system.commons.core.domain.valueobject.Money;
import com.hotel.booking.system.commons.core.message.ApplicationMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@Tags({@Tag("unit")})
@DisplayName("Room entity validation tests")
@ExtendWith(MockitoExtension.class)
class RoomValidationTest {

  private static final String ROOM_NAME = "Quarto Duplo Superior";
  private static final int ROOM_CAPACITY = 2;
  private static final String ROOM_DESCRIPTION = "This double room has a tile/marble floor and air conditioning.";
  private static final Money ROOM_MONEY = Money.of(350);
  private static final HotelId HOTEL_ID = HotelId.of("6641cd32-df28-4f3e-b709-a6d568bc6d90");
  public static final int ROOM_QUANTITY = 1;

  @BeforeEach
  void setUp() {
  }

  @Test
  @DisplayName("Should validate Room attributes")
  void test1() {
    final Room sut = Room.builder()
      .hotelId(HOTEL_ID)
      .name(ROOM_NAME)
      .description(ROOM_DESCRIPTION)
      .capacity(ROOM_CAPACITY)
      .currentPrice(ROOM_MONEY)
      .quantity(ROOM_QUANTITY)
      .build();
    Assertions.assertThatCode(() -> sut.validate())
      .doesNotThrowAnyException();
  }

  @Test
  @DisplayName("Should validate Room name")
  void test2() {
    final Room sut = Room.builder()
      .hotelId(HOTEL_ID)
      .name(null)
      .description(ROOM_DESCRIPTION)
      .capacity(ROOM_CAPACITY)
      .currentPrice(ROOM_MONEY)
      .build();
    Assertions.assertThatThrownBy(sut::validate)
      .hasMessage(ApplicationMessage.HOTEL_ROOM_NAME_NOT_NULL);
  }

  @Test
  @DisplayName("Should validate Room description")
  void test3() {
    final Room sut = Room.builder()
      .hotelId(HOTEL_ID)
      .name(ROOM_NAME)
      .description(null)
      .capacity(ROOM_CAPACITY)
      .currentPrice(ROOM_MONEY)
      .build();
    Assertions.assertThatThrownBy(sut::validate)
      .hasMessage(ApplicationMessage.HOTEL_ROOM_DESCRIPTION_NOT_NULL);
  }

  @Test
  @DisplayName("Should validate Room null capacity")
  void test4() {
    final Room sut = Room.builder()
      .hotelId(HOTEL_ID)
      .name(ROOM_NAME)
      .description(ROOM_DESCRIPTION)
      .capacity(null)
      .currentPrice(ROOM_MONEY)
      .build();
    Assertions.assertThatThrownBy(sut::validate)
      .hasMessage(ApplicationMessage.HOTEL_ROOM_CAPACITY_NOT_NULL);
  }

  @Test
  @DisplayName("Should validate Room negative capacity")
  void test5() {
    final Room sut = Room.builder()
      .hotelId(HOTEL_ID)
      .name(ROOM_NAME)
      .description(ROOM_DESCRIPTION)
      .capacity(-ROOM_QUANTITY)
      .currentPrice(ROOM_MONEY)
      .build();
    Assertions.assertThatThrownBy(sut::validate)
      .hasMessage(ApplicationMessage.HOTEL_ROOM_CAPACITY_INVALID);
  }

  @Test
  @DisplayName("Should validate Room zero capacity")
  void test6() {
    final Room sut = Room.builder()
      .hotelId(HOTEL_ID)
      .name(ROOM_NAME)
      .description(ROOM_DESCRIPTION)
      .capacity(0)
      .currentPrice(ROOM_MONEY)
      .build();
    Assertions.assertThatThrownBy(sut::validate)
      .hasMessage(ApplicationMessage.HOTEL_ROOM_CAPACITY_INVALID);
  }

  @Test
  @DisplayName("Should validate Room null current price")
  void test7() {
    final Room sut = Room.builder()
      .hotelId(HOTEL_ID)
      .name(ROOM_NAME)
      .description(ROOM_DESCRIPTION)
      .capacity(ROOM_CAPACITY)
      .currentPrice(null)
      .build();
    Assertions.assertThatThrownBy(sut::validate)
      .hasMessage(ApplicationMessage.HOTEL_ROOM_CURRENT_PRICE_NOT_NULL);
  }

  @Test
  @DisplayName("Should validate Room zero current price")
  void test8() {
    final Room sut = Room.builder()
      .hotelId(HOTEL_ID)
      .name(ROOM_NAME)
      .description(ROOM_DESCRIPTION)
      .capacity(ROOM_CAPACITY)
      .currentPrice(Money.ZERO)
      .build();
    Assertions.assertThatThrownBy(sut::validate)
      .hasMessage(ApplicationMessage.HOTEL_ROOM_CURRENT_PRICE_INVALID);
  }

  @Test
  @DisplayName("Should validate Room negative current price")
  void test9() {
    final Room sut = Room.builder()
      .hotelId(HOTEL_ID)
      .name(ROOM_NAME)
      .description(ROOM_DESCRIPTION)
      .capacity(ROOM_CAPACITY)
      .currentPrice(Money.of(-ROOM_QUANTITY))
      .build();
    Assertions.assertThatThrownBy(sut::validate)
      .hasMessage(ApplicationMessage.HOTEL_ROOM_CURRENT_PRICE_INVALID);
  }


}
