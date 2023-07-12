package com.hotel.booking.system.hotel.service.core.domain.entity;

import com.hotel.booking.system.commons.core.domain.valueobject.Money;
import com.hotel.booking.system.commons.core.domain.valueobject.RoomId;
import com.hotel.booking.system.commons.core.message.ApplicationMessage;
import com.hotel.booking.system.hotel.service.core.domain.exception.HotelDomainException;
import com.hotel.booking.system.hotel.service.core.domain.valueobject.HotelAddress;
import com.hotel.booking.system.hotel.service.core.domain.valueobject.HotelCategoryId;
import com.hotel.booking.system.hotel.service.core.domain.valueobject.HotelId;
import com.hotel.booking.system.hotel.service.core.domain.valueobject.LocalityId;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@Tags({@Tag("unit")})
@DisplayName("Hotel entity validation tests")
@ExtendWith(MockitoExtension.class)
class HotelValidationTest {

  private static final String HOTEL_RAW_ID = "ebaf3dbb-9b32-4dba-8872-becccd579ff9";
  private static final HotelId HOTEL_ID = HotelId.of(HOTEL_RAW_ID);
  private static final String HOTEL_CATEGORY_RAW_ID = "44ba9f79-a581-441a-bc55-28a3cda3a8da";
  private static final HotelCategoryId CATEGORY_ID = HotelCategoryId.of(HOTEL_CATEGORY_RAW_ID);
  private static final String LOCALITY_RAW_ID = "ae348be4-4ccb-41c0-b00a-40363bc09cbb";
  private static final LocalityId LOCALITY_ID = LocalityId.of(LOCALITY_RAW_ID);
  private static final String HOTEL_NAME = "Pousada Portal";
  private static final String HOTEL_DESCRIPTION = "Localizada a 600 m do centro da cidade da Chapada dos Guimarães, " +
                                                  "esta pousada dispõe de uma cozinha de uso comum e lounge com TV. A" +
                                                  " propriedade oferece café-da-manhã, serviço de transporte e " +
                                                  "recepção 24 horas.";
  private Hotel sut;

  @BeforeEach
  void setUp() {
  }

  @Test
  @DisplayName("Should validate Hotel attributes")
  void test1() {
    this.sut = new Hotel(
      HOTEL_ID,
      HOTEL_NAME,
      HOTEL_DESCRIPTION,
      CATEGORY_ID,
      new HotelAddress("78195-000", "Av Perimetral, 766"),
      LOCALITY_ID,
      Rooms.of(
        Room.builder()
          .id(RoomId.newInstance())
          .hotelId(HOTEL_ID)
          .name("Quarto Duplo Superior")
          .capacity(2)
          .description("This double room has a tile/marble floor and air conditioning.")
          .currentPrice(Money.of(350))
          .quantity(2)
          .build(),
        Room.builder()
          .id(RoomId.newInstance())
          .hotelId(HOTEL_ID)
          .name("Quarto Triplo Standard")
          .capacity(3)
          .description("This triple room has air conditioning, dining area and tile/marble floor.")
          .currentPrice(Money.of(450))
          .quantity(3)
          .build(),
        Room.builder()
          .id(RoomId.newInstance())
          .hotelId(HOTEL_ID)
          .name("Quarto Família Superior")
          .capacity(4)
          .description("Este quarto família dispõe de frigobar, piso frio/de mármore e ar-condicionado.")
          .currentPrice(Money.of(550))
          .quantity(1)
          .build()
      )
    );
    Assertions.assertThatCode(() -> this.sut.validate())
      .doesNotThrowAnyException();
  }

  @Test
  @DisplayName("Should validate Hotel name")
  void test2() {
    this.sut = new Hotel(
      HOTEL_ID,
      null,
      HOTEL_DESCRIPTION,
      CATEGORY_ID,
      new HotelAddress("78195-000", "Av Perimetral, 766"),
      LOCALITY_ID,
      Rooms.of(
        Room.builder()
          .id(RoomId.newInstance())
          .hotelId(HOTEL_ID)
          .name("Quarto Duplo Superior")
          .capacity(2)
          .description("This double room has a tile/marble floor and air conditioning.")
          .currentPrice(Money.of(350))
          .build(),
        Room.builder()
          .id(RoomId.newInstance())
          .hotelId(HOTEL_ID)
          .name("Quarto Triplo Standard")
          .capacity(3)
          .description("This triple room has air conditioning, dining area and tile/marble floor.")
          .currentPrice(Money.of(450))
          .build(),
        Room.builder()
          .id(RoomId.newInstance())
          .hotelId(HOTEL_ID)
          .name("Quarto Família Superior")
          .capacity(4)
          .description("Este quarto família dispõe de frigobar, piso frio/de mármore e ar-condicionado.")
          .currentPrice(Money.of(550))
          .build()
      )
    );
    Assertions.assertThatThrownBy(() -> this.sut.validate())
      .isInstanceOf(HotelDomainException.class)
      .hasMessage(ApplicationMessage.HOTEL_NAME_INVALID);
  }

  @Test
  @DisplayName("Should validate Hotel description")
  void test3() {
    this.sut = new Hotel(
      HOTEL_ID,
      HOTEL_NAME,
      null,
      CATEGORY_ID,
      new HotelAddress("78195-000", "Av Perimetral, 766"),
      LOCALITY_ID,
      Rooms.of(
        Room.builder()
          .id(RoomId.newInstance())
          .hotelId(HOTEL_ID)
          .name("Quarto Duplo Superior")
          .capacity(2)
          .description("This double room has a tile/marble floor and air conditioning.")
          .currentPrice(Money.of(350))
          .build(),
        Room.builder()
          .id(RoomId.newInstance())
          .hotelId(HOTEL_ID)
          .name("Quarto Triplo Standard")
          .capacity(3)
          .description("This triple room has air conditioning, dining area and tile/marble floor.")
          .currentPrice(Money.of(450))
          .build(),
        Room.builder()
          .id(RoomId.newInstance())
          .hotelId(HOTEL_ID)
          .name("Quarto Família Superior")
          .capacity(4)
          .description("Este quarto família dispõe de frigobar, piso frio/de mármore e ar-condicionado.")
          .currentPrice(Money.of(550))
          .build()
      )
    );
    Assertions.assertThatThrownBy(() -> this.sut.validate())
      .isInstanceOf(HotelDomainException.class)
      .hasMessage(ApplicationMessage.HOTEL_DESCRIPTION_INVALID);
  }

  @Test
  @DisplayName("Should validate Hotel cep")
  void test4() {
    this.sut = new Hotel(
      HOTEL_ID,
      HOTEL_NAME,
      HOTEL_DESCRIPTION,
      CATEGORY_ID,
      new HotelAddress("78195-aaa", "Av Perimetral, 766"),
      LOCALITY_ID,
      Rooms.of(
        Room.builder()
          .id(RoomId.newInstance())
          .hotelId(HOTEL_ID)
          .name("Quarto Duplo Superior")
          .capacity(2)
          .description("This double room has a tile/marble floor and air conditioning.")
          .currentPrice(Money.of(350))
          .build(),
        Room.builder()
          .id(RoomId.newInstance())
          .hotelId(HOTEL_ID)
          .name("Quarto Triplo Standard")
          .capacity(3)
          .description("This triple room has air conditioning, dining area and tile/marble floor.")
          .currentPrice(Money.of(450))
          .build(),
        Room.builder()
          .id(RoomId.newInstance())
          .hotelId(HOTEL_ID)
          .name("Quarto Família Superior")
          .capacity(4)
          .description("Este quarto família dispõe de frigobar, piso frio/de mármore e ar-condicionado.")
          .currentPrice(Money.of(550))
          .build()
      )
    );
    Assertions.assertThatThrownBy(() -> this.sut.validate())
      .isInstanceOf(HotelDomainException.class)
      .hasMessage(ApplicationMessage.HOTEL_CEP_INVALID);
  }

  @Test
  @DisplayName("Should validate Hotel street")
  void test5() {
    this.sut = new Hotel(
      HOTEL_ID,
      HOTEL_NAME,
      HOTEL_DESCRIPTION,
      CATEGORY_ID,
      new HotelAddress("78195-000", ""),
      LOCALITY_ID,
      Rooms.of(
        Room.builder()
          .id(RoomId.newInstance())
          .hotelId(HOTEL_ID)
          .name("Quarto Duplo Superior")
          .capacity(2)
          .description("This double room has a tile/marble floor and air conditioning.")
          .currentPrice(Money.of(350))
          .build(),
        Room.builder()
          .id(RoomId.newInstance())
          .hotelId(HOTEL_ID)
          .name("Quarto Triplo Standard")
          .capacity(3)
          .description("This triple room has air conditioning, dining area and tile/marble floor.")
          .currentPrice(Money.of(450))
          .build(),
        Room.builder()
          .id(RoomId.newInstance())
          .hotelId(HOTEL_ID)
          .name("Quarto Família Superior")
          .capacity(4)
          .description("Este quarto família dispõe de frigobar, piso frio/de mármore e ar-condicionado.")
          .currentPrice(Money.of(550))
          .build()
      )
    );
    Assertions.assertThatThrownBy(() -> this.sut.validate())
      .isInstanceOf(HotelDomainException.class)
      .hasMessage(ApplicationMessage.HOTEL_STREET_INVALID);
  }

  @Test
  @DisplayName("Should validate Hotel Rooms")
  void test6() {
    this.sut = new Hotel(
      HOTEL_ID,
      HOTEL_NAME,
      HOTEL_DESCRIPTION,
      CATEGORY_ID,
      new HotelAddress("78195-000", "Av Perimetral, 766"),
      LOCALITY_ID,
      Rooms.empty()
    );
    Assertions.assertThatThrownBy(() -> this.sut.validate())
      .isInstanceOf(HotelDomainException.class)
      .hasMessage(ApplicationMessage.HOTEL_ROOMS_INVALID);
  }

}
