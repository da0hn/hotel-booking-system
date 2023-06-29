package com.hotel.booking.system.hotel.service.core.domain.application.usecase;

import com.hotel.booking.system.commons.core.message.ApplicationMessage;
import com.hotel.booking.system.hotel.service.core.application.dto.RegisterHotelInput;
import com.hotel.booking.system.hotel.service.core.application.dto.RegisterHotelOutput;
import com.hotel.booking.system.hotel.service.core.application.dto.RegisterHotelRoomInput;
import com.hotel.booking.system.hotel.service.core.application.mapper.HotelUseCaseMapperImpl;
import com.hotel.booking.system.hotel.service.core.application.usecase.RegisterHotelUseCaseImpl;
import com.hotel.booking.system.hotel.service.core.domain.entity.Hotel;
import com.hotel.booking.system.hotel.service.core.domain.exception.HotelDomainException;
import com.hotel.booking.system.hotel.service.core.domain.valueobject.HotelCategoryId;
import com.hotel.booking.system.hotel.service.core.domain.valueobject.LocalityId;
import com.hotel.booking.system.hotel.service.core.ports.api.RegisterHotelUseCase;
import com.hotel.booking.system.hotel.service.core.ports.spi.HotelRepository;
import com.hotel.booking.system.hotel.service.core.ports.spi.LocalityRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

@Tags({@Tag("unit")})
@DisplayName("Register hotel use case tests")
@ExtendWith(MockitoExtension.class)
class RegisterHotelUseCaseImplTest {


  private static final String CATEGORY_ID = "e1bf8402-9441-4774-8061-9444fc8bdb28";
  private static final String LOCALITY_ID = "9898b2f1-9861-42c5-a84f-42bba41fbe00";
  private RegisterHotelUseCase sut;
  @Mock
  private HotelRepository hotelRepository;
  @Mock
  private LocalityRepository localityRepository;

  @BeforeEach
  void setUp() {
    this.sut = new RegisterHotelUseCaseImpl(
      this.hotelRepository,
      this.localityRepository,
      new HotelUseCaseMapperImpl()
    );
  }

  @Test
  @DisplayName("Should create a new Hotel with rooms")
  void test1() {

    Mockito.doReturn(true)
      .when(this.hotelRepository)
      .existsCategoryById(Mockito.any(HotelCategoryId.class));
    Mockito.doReturn(true)
      .when(this.localityRepository)
      .existsLocalityById(Mockito.any(LocalityId.class));
    Mockito.doNothing()
      .when(this.hotelRepository)
      .register(Mockito.any(Hotel.class));

    final RegisterHotelOutput output = this.sut.execute(
      RegisterHotelInput.builder()
        .name("Pousada Portal")
        .description(
          "Localizada a 600 m do centro da cidade da Chapada dos Guimarães, esta pousada dispõe de uma cozinha de uso comum e lounge com TV. A propriedade oferece café-da-manhã, serviço de transporte e recepção 24 horas."
        )
        .categoryId(CATEGORY_ID)
        .localityId(LOCALITY_ID)
        .street("Av Perimetral, 766")
        .cep("78195-000")
        .rooms(List.of(
          RegisterHotelRoomInput.builder()
            .name("Quarto Duplo Superior")
            .capacity(2)
            .description("This double room has a tile/marble floor and air conditioning.")
            .currentPrice(BigDecimal.valueOf(350))
            .build(),
          RegisterHotelRoomInput.builder()
            .name("Quarto Triplo Standard")
            .capacity(3)
            .description("This triple room has air conditioning, dining area and tile/marble floor.")
            .currentPrice(BigDecimal.valueOf(450))
            .build(),
          RegisterHotelRoomInput.builder()
            .name("Quarto Família Superior")
            .capacity(4)
            .description("Este quarto família dispõe de frigobar, piso frio/de mármore e ar-condicionado.")
            .currentPrice(BigDecimal.valueOf(550))
            .build()
        ))
        .build()
    );

    Mockito.verify(this.hotelRepository, Mockito.times(1))
      .existsCategoryById(Mockito.any(HotelCategoryId.class));
    Mockito.verify(this.localityRepository, Mockito.times(1))
      .existsLocalityById(Mockito.any(LocalityId.class));
    Mockito.verify(this.hotelRepository, Mockito.times(1))
      .register(Mockito.any(Hotel.class));

    Assertions.assertThat(output.hotelId()).isNotNull();
    Assertions.assertThat(output.roomsId()).hasSize(3);
  }

  @Test
  @DisplayName("Should throw exception when category not exists")
  void test2() {

    Mockito.doReturn(false)
      .when(this.hotelRepository)
      .existsCategoryById(Mockito.any(HotelCategoryId.class));

    final var input = RegisterHotelInput.builder()
      .name("Pousada Portal")
      .description(
        "Localizada a 600 m do centro da cidade da Chapada dos Guimarães, esta pousada dispõe de uma cozinha de uso comum e lounge com TV. A propriedade oferece café-da-manhã, serviço de transporte e recepção 24 horas."
      )
      .categoryId(CATEGORY_ID)
      .localityId(LOCALITY_ID)
      .street("Av Perimetral, 766")
      .cep("78195-000")
      .rooms(List.of(
        RegisterHotelRoomInput.builder()
          .name("Quarto Duplo Superior")
          .capacity(2)
          .description("This double room has a tile/marble floor and air conditioning.")
          .currentPrice(BigDecimal.valueOf(350))
          .build(),
        RegisterHotelRoomInput.builder()
          .name("Quarto Triplo Standard")
          .capacity(3)
          .description("This triple room has air conditioning, dining area and tile/marble floor.")
          .currentPrice(BigDecimal.valueOf(450))
          .build(),
        RegisterHotelRoomInput.builder()
          .name("Quarto Família Superior")
          .capacity(4)
          .description("Este quarto família dispõe de frigobar, piso frio/de mármore e ar-condicionado.")
          .currentPrice(BigDecimal.valueOf(550))
          .build()
      ))
      .build();

    Assertions.assertThatThrownBy(() -> this.sut.execute(input))
      .isInstanceOf(HotelDomainException.class)
      .hasMessage(ApplicationMessage.HOTEL_CATEGORY_NOT_FOUND);

    Mockito.verify(this.hotelRepository, Mockito.times(1))
      .existsCategoryById(Mockito.any(HotelCategoryId.class));
    Mockito.verify(this.localityRepository, Mockito.never())
      .existsLocalityById(Mockito.any(LocalityId.class));
    Mockito.verify(this.hotelRepository, Mockito.never())
      .register(Mockito.any(Hotel.class));
  }

  @Test
  @DisplayName("Should throw exception when locality not exists")
  void test3() {

    Mockito.doReturn(true)
      .when(this.hotelRepository)
      .existsCategoryById(Mockito.any(HotelCategoryId.class));
    Mockito.doReturn(false)
      .when(this.localityRepository)
      .existsLocalityById(Mockito.any(LocalityId.class));

    final var input = RegisterHotelInput.builder()
      .name("Pousada Portal")
      .description(
        "Localizada a 600 m do centro da cidade da Chapada dos Guimarães, esta pousada dispõe de uma cozinha de uso comum e lounge com TV. A propriedade oferece café-da-manhã, serviço de transporte e recepção 24 horas."
      )
      .categoryId(CATEGORY_ID)
      .localityId(LOCALITY_ID)
      .street("Av Perimetral, 766")
      .cep("78195-000")
      .rooms(List.of(
        RegisterHotelRoomInput.builder()
          .name("Quarto Duplo Superior")
          .capacity(2)
          .description("This double room has a tile/marble floor and air conditioning.")
          .currentPrice(BigDecimal.valueOf(350))
          .build(),
        RegisterHotelRoomInput.builder()
          .name("Quarto Triplo Standard")
          .capacity(3)
          .description("This triple room has air conditioning, dining area and tile/marble floor.")
          .currentPrice(BigDecimal.valueOf(450))
          .build(),
        RegisterHotelRoomInput.builder()
          .name("Quarto Família Superior")
          .capacity(4)
          .description("Este quarto família dispõe de frigobar, piso frio/de mármore e ar-condicionado.")
          .currentPrice(BigDecimal.valueOf(550))
          .build()
      ))
      .build();
    Assertions.assertThatThrownBy(() -> this.sut.execute(input))
      .isInstanceOf(HotelDomainException.class)
      .hasMessage(ApplicationMessage.HOTEL_LOCALITY_NOT_FOUND);

    Mockito.verify(this.hotelRepository, Mockito.times(1))
      .existsCategoryById(Mockito.any(HotelCategoryId.class));
    Mockito.verify(this.localityRepository, Mockito.times(1))
      .existsLocalityById(Mockito.any(LocalityId.class));
    Mockito.verify(this.hotelRepository, Mockito.never())
      .register(Mockito.any(Hotel.class));
  }

}
