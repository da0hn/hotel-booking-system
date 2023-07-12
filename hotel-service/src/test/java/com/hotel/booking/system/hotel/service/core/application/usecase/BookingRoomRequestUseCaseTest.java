package com.hotel.booking.system.hotel.service.core.application.usecase;

import com.hotel.booking.system.commons.core.domain.event.BookingRoomInitiatedEvent;
import com.hotel.booking.system.commons.core.domain.event.BookingRoomRequestedEvent;
import com.hotel.booking.system.commons.core.domain.valueobject.CustomerReservationStatus;
import com.hotel.booking.system.commons.core.domain.valueobject.Money;
import com.hotel.booking.system.commons.core.message.ApplicationMessage;
import com.hotel.booking.system.hotel.service.core.application.dto.BookRoomItemInput;
import com.hotel.booking.system.hotel.service.core.application.dto.BookingRoomInput;
import com.hotel.booking.system.hotel.service.core.domain.entity.Room;
import com.hotel.booking.system.hotel.service.core.domain.entity.Rooms;
import com.hotel.booking.system.hotel.service.core.domain.exception.HotelDomainException;
import com.hotel.booking.system.hotel.service.core.domain.valueobject.RoomId;
import com.hotel.booking.system.hotel.service.core.ports.api.usecase.BookingRoomRequestUseCase;
import com.hotel.booking.system.hotel.service.core.ports.spi.messaging.BookingRoomRequestedPublisher;
import com.hotel.booking.system.hotel.service.core.ports.spi.messaging.CustomerBookingRoomUpdatePublisher;
import com.hotel.booking.system.hotel.service.core.ports.spi.repository.HotelRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Tags({@Tag("unit")})
@DisplayName("Book Room use case tests")
@ExtendWith(MockitoExtension.class)
class BookingRoomRequestUseCaseTest {

  public static final Money CURRENT_PRICE_250 = Money.of(250);
  public static final Money CURRENT_PRICE_500 = Money.of(500);
  public static final BigDecimal ROOM_TOTAL_PRICE = new BigDecimal(750);
  public static final int TOTAL_GUESTS = 3;
  public static final int TOTAL_ROOMS = 2;
  private static final String ROOM_ID_1 = "030bac34-af07-43ac-a708-6fb309e88ace";
  private static final String ROOM_ID_2 = "d86d17a0-1819-4cb0-9562-0fcf7480110f";
  private static final String CUSTOMER_ID = "4daa2569-3012-455c-9ded-291b45118810";
  private BookingRoomRequestUseCase sut;
  @Mock
  private HotelRepository hotelRepository;
  @Mock
  private CustomerBookingRoomUpdatePublisher customerBookingRoomUpdatePublisher;
  @Mock
  private BookingRoomRequestedPublisher bookingRoomRequestedPublisher;

  @BeforeEach
  void setUp() {
    this.sut = new BookingRoomRequestUseCaseImpl(
      this.hotelRepository,
      this.customerBookingRoomUpdatePublisher,
      this.bookingRoomRequestedPublisher
    );
  }

  @Test
  @DisplayName("Should validate Booking Room request")
  void test1() {

    Mockito.doReturn(Rooms.of(
        Room.builder()
          .id(RoomId.of(ROOM_ID_1))
          .capacity(TOTAL_ROOMS)
          .currentPrice(CURRENT_PRICE_250)
          .build(),
        Room.builder()
          .id(RoomId.of(ROOM_ID_2))
          .capacity(TOTAL_ROOMS)
          .currentPrice(CURRENT_PRICE_500)
          .build()
      ))
      .when(this.hotelRepository)
      .findAllRoomsById(Mockito.any());

    final var output = this.sut.execute(
      BookingRoomInput.builder()
        .customerId(CUSTOMER_ID)
        .checkIn(LocalDate.now().minusDays(10))
        .checkOut(LocalDate.now().plusDays(5))
        .guests(TOTAL_GUESTS)
        .rooms(Set.of(
          BookRoomItemInput.builder()
            .roomId(ROOM_ID_1)
            .roomQuantity(1)
            .build(),
          BookRoomItemInput.builder()
            .roomId(ROOM_ID_2)
            .roomQuantity(1)
            .build()
        ))
        .build()
    );

    Mockito.verify(this.bookingRoomRequestedPublisher, Mockito.times(1))
      .publish(Mockito.any(BookingRoomRequestedEvent.class));
    Mockito.verify(this.customerBookingRoomUpdatePublisher, Mockito.times(1))
      .publish(Mockito.any(BookingRoomInitiatedEvent.class));

    Assertions.assertThat(output.reservationOrderId())
      .isNotNull();
  }

  @Test
  @DisplayName("Should throw exception when room capacity was exceeded")
  void test2() {

    Mockito.doReturn(Rooms.of(
        Room.builder()
          .id(RoomId.of(ROOM_ID_1))
          .capacity(TOTAL_ROOMS)
          .currentPrice(CURRENT_PRICE_250)
          .build(),
        Room.builder()
          .id(RoomId.of(ROOM_ID_2))
          .capacity(TOTAL_ROOMS)
          .currentPrice(CURRENT_PRICE_500)
          .build()
      ))
      .when(this.hotelRepository)
      .findAllRoomsById(Mockito.any());

    final var input = BookingRoomInput.builder()
      .customerId(CUSTOMER_ID)
      .checkIn(LocalDate.now().minusDays(10))
      .checkOut(LocalDate.now().plusDays(5))
      .guests(TOTAL_GUESTS)
      .rooms(Set.of(
        BookRoomItemInput.builder()
          .roomId(ROOM_ID_1)
          .roomQuantity(1)
          .build(),
        BookRoomItemInput.builder()
          .roomId(ROOM_ID_2)
          .roomQuantity(TOTAL_GUESTS)
          .build()
      ))
      .build();

    Mockito.verify(this.bookingRoomRequestedPublisher, Mockito.never())
      .publish(Mockito.any(BookingRoomRequestedEvent.class));
    Mockito.verify(this.customerBookingRoomUpdatePublisher, Mockito.never())
      .publish(Mockito.any(BookingRoomInitiatedEvent.class));

    Assertions.assertThatThrownBy(() -> this.sut.execute(input))
      .isInstanceOf(HotelDomainException.class)
      .hasMessage(ApplicationMessage.HOTEL_ROOM_CAPACITY_EXCEEDED);
  }

  @Test
  @DisplayName("Should throw exception when informed room was not found")
  void test3() {

    Mockito.doReturn(Rooms.of(
        Room.builder()
          .id(RoomId.of(ROOM_ID_1))
          .capacity(TOTAL_ROOMS)
          .currentPrice(CURRENT_PRICE_250)
          .build()
      ))
      .when(this.hotelRepository)
      .findAllRoomsById(Mockito.any());

    final var input = BookingRoomInput.builder()
      .customerId(CUSTOMER_ID)
      .checkIn(LocalDate.now().minusDays(10))
      .checkOut(LocalDate.now().plusDays(5))
      .guests(TOTAL_GUESTS)
      .rooms(Set.of(
        BookRoomItemInput.builder()
          .roomId(ROOM_ID_1)
          .roomQuantity(1)
          .build(),
        BookRoomItemInput.builder()
          .roomId(ROOM_ID_2)
          .roomQuantity(TOTAL_ROOMS)
          .build()
      ))
      .build();

    Mockito.verify(this.bookingRoomRequestedPublisher, Mockito.never())
      .publish(Mockito.any(BookingRoomRequestedEvent.class));
    Mockito.verify(this.customerBookingRoomUpdatePublisher, Mockito.never())
      .publish(Mockito.any(BookingRoomInitiatedEvent.class));

    Assertions.assertThatThrownBy(() -> this.sut.execute(input))
      .isInstanceOf(HotelDomainException.class)
      .hasMessage(ApplicationMessage.HOTEL_ROOM_NOT_FOUND);
  }

  @Test
  @DisplayName("Should calculate correctly total price of BookingRoomRequestedEvent")
  void test4() {

    Mockito.doReturn(Rooms.of(
        Room.builder()
          .id(RoomId.of(ROOM_ID_1))
          .capacity(TOTAL_ROOMS)
          .currentPrice(CURRENT_PRICE_250)
          .build(),
        Room.builder()
          .id(RoomId.of(ROOM_ID_2))
          .capacity(TOTAL_ROOMS)
          .currentPrice(CURRENT_PRICE_500)
          .build()
      ))
      .when(this.hotelRepository)
      .findAllRoomsById(Mockito.any());

    final var output = this.sut.execute(
      BookingRoomInput.builder()
        .customerId(CUSTOMER_ID)
        .checkIn(LocalDate.now().minusDays(10))
        .checkOut(LocalDate.now().plusDays(5))
        .guests(TOTAL_GUESTS)
        .rooms(Set.of(
          BookRoomItemInput.builder()
            .roomId(ROOM_ID_1)
            .roomQuantity(1)
            .build(),
          BookRoomItemInput.builder()
            .roomId(ROOM_ID_2)
            .roomQuantity(1)
            .build()
        ))
        .build()
    );

    final var roomBookingRequestedEventArgumentCaptor = ArgumentCaptor.forClass(BookingRoomRequestedEvent.class);

    Mockito.verify(this.bookingRoomRequestedPublisher, Mockito.times(1))
      .publish(roomBookingRequestedEventArgumentCaptor.capture());
    Mockito.verify(this.customerBookingRoomUpdatePublisher, Mockito.times(1))
      .publish(Mockito.any(BookingRoomInitiatedEvent.class));

    final var capturedEvent = roomBookingRequestedEventArgumentCaptor.getValue();

    Assertions.assertThat(capturedEvent)
      .extracting(BookingRoomRequestedEvent::getPrice)
      .satisfies(price -> Assertions.assertThat(price).isEqualByComparingTo(ROOM_TOTAL_PRICE));

    Assertions.assertThat(output.reservationOrderId())
      .isNotNull();
  }

  @Test
  @DisplayName("Should validate Booking Room guests capacity")
  void test5() {

    Mockito.doReturn(Rooms.of(
        Room.builder()
          .id(RoomId.of(ROOM_ID_1))
          .capacity(TOTAL_ROOMS)
          .currentPrice(CURRENT_PRICE_250)
          .build(),
        Room.builder()
          .id(RoomId.of(ROOM_ID_2))
          .capacity(TOTAL_ROOMS)
          .currentPrice(CURRENT_PRICE_500)
          .build()
      ))
      .when(this.hotelRepository)
      .findAllRoomsById(Mockito.any());

    final var input = BookingRoomInput.builder()
      .customerId(CUSTOMER_ID)
      .checkIn(LocalDate.now().minusDays(10))
      .checkOut(LocalDate.now().plusDays(5))
      .guests(5)
      .rooms(Set.of(
        BookRoomItemInput.builder()
          .roomId(ROOM_ID_1)
          .roomQuantity(1)
          .build(),
        BookRoomItemInput.builder()
          .roomId(ROOM_ID_2)
          .roomQuantity(1)
          .build()
      ))
      .build();

    Assertions.assertThatThrownBy(() -> this.sut.execute(input))
      .isInstanceOf(HotelDomainException.class)
      .hasMessage(ApplicationMessage.HOTEL_BOOKING_GUESTS_EXCEEDED);

    Mockito.verify(this.bookingRoomRequestedPublisher, Mockito.never())
      .publish(Mockito.any(BookingRoomRequestedEvent.class));
    Mockito.verify(this.customerBookingRoomUpdatePublisher, Mockito.never())
      .publish(Mockito.any(BookingRoomInitiatedEvent.class));


  }

  @Test
  @DisplayName("Should calculate correctly total price of BookingRoomInitiatedEvent")
  void test6() {

    Mockito.doReturn(Rooms.of(
        Room.builder()
          .id(RoomId.of(ROOM_ID_1))
          .capacity(TOTAL_ROOMS)
          .currentPrice(CURRENT_PRICE_250)
          .build(),
        Room.builder()
          .id(RoomId.of(ROOM_ID_2))
          .capacity(TOTAL_ROOMS)
          .currentPrice(CURRENT_PRICE_500)
          .build()
      ))
      .when(this.hotelRepository)
      .findAllRoomsById(Mockito.any());

    final var output = this.sut.execute(
      BookingRoomInput.builder()
        .customerId(CUSTOMER_ID)
        .checkIn(LocalDate.now().minusDays(10))
        .checkOut(LocalDate.now().plusDays(5))
        .guests(TOTAL_GUESTS)
        .rooms(Set.of(
          BookRoomItemInput.builder()
            .roomId(ROOM_ID_1)
            .roomQuantity(1)
            .build(),
          BookRoomItemInput.builder()
            .roomId(ROOM_ID_2)
            .roomQuantity(1)
            .build()
        ))
        .build()
    );

    final var roomBookingInitiatedEventArgumentCaptor = ArgumentCaptor.forClass(BookingRoomInitiatedEvent.class);

    Mockito.verify(this.bookingRoomRequestedPublisher, Mockito.times(1))
      .publish(Mockito.any());
    Mockito.verify(this.customerBookingRoomUpdatePublisher, Mockito.times(1))
      .publish(roomBookingInitiatedEventArgumentCaptor.capture());

    final var capturedEvent = roomBookingInitiatedEventArgumentCaptor.getValue();

    Assertions.assertThat(capturedEvent)
      .extracting(BookingRoomInitiatedEvent::getTotalPrice)
      .satisfies(price -> Assertions.assertThat(price).isEqualByComparingTo(ROOM_TOTAL_PRICE));

    Assertions.assertThat(output.reservationOrderId())
      .isNotNull();
  }

  @Test
  @DisplayName("Should create correctly event BookingRoomInitiatedEvent")
  void test7() {

    Mockito.doReturn(Rooms.of(
        Room.builder()
          .id(RoomId.of(ROOM_ID_1))
          .capacity(TOTAL_ROOMS)
          .currentPrice(CURRENT_PRICE_250)
          .build(),
        Room.builder()
          .id(RoomId.of(ROOM_ID_2))
          .capacity(TOTAL_ROOMS)
          .currentPrice(CURRENT_PRICE_500)
          .build()
      ))
      .when(this.hotelRepository)
      .findAllRoomsById(Mockito.any());

    this.sut.execute(
      BookingRoomInput.builder()
        .customerId(CUSTOMER_ID)
        .checkIn(LocalDate.now().minusDays(10))
        .checkOut(LocalDate.now().plusDays(5))
        .guests(TOTAL_GUESTS)
        .rooms(Set.of(
          BookRoomItemInput.builder()
            .roomId(ROOM_ID_1)
            .roomQuantity(1)
            .build(),
          BookRoomItemInput.builder()
            .roomId(ROOM_ID_2)
            .roomQuantity(1)
            .build()
        ))
        .build()
    );

    final var roomBookingInitiatedEventArgumentCaptor = ArgumentCaptor.forClass(BookingRoomInitiatedEvent.class);

    Mockito.verify(this.bookingRoomRequestedPublisher, Mockito.times(1))
      .publish(Mockito.any());
    Mockito.verify(this.customerBookingRoomUpdatePublisher, Mockito.times(1))
      .publish(roomBookingInitiatedEventArgumentCaptor.capture());

    final var capturedEvent = roomBookingInitiatedEventArgumentCaptor.getValue();

    Assertions.assertThat(capturedEvent)
      .satisfies(
        event -> Assertions.assertThat(event).isNotNull(),
        event -> Assertions.assertThat(event.getStatus()).isEqualTo(
          CustomerReservationStatus.AWAITING_RESERVATION),
        event -> Assertions.assertThat(event.getCustomerId()).isEqualTo(
          CUSTOMER_ID),
        event -> Assertions.assertThat(event.getGuests()).isEqualTo(TOTAL_GUESTS),
        event -> Assertions.assertThat(event.getRooms()).hasSize(TOTAL_ROOMS),
        event -> Assertions.assertThat(event.getReservationOrderId()).isNotNull()
      );
  }

  @Test
  @DisplayName("Should create correctly event BookingRoomRequestedEvent")
  void test8() {

    Mockito.doReturn(Rooms.of(
        Room.builder()
          .id(RoomId.of(ROOM_ID_1))
          .capacity(TOTAL_ROOMS)
          .currentPrice(CURRENT_PRICE_250)
          .build(),
        Room.builder()
          .id(RoomId.of(ROOM_ID_2))
          .capacity(TOTAL_ROOMS)
          .currentPrice(CURRENT_PRICE_500)
          .build()
      ))
      .when(this.hotelRepository)
      .findAllRoomsById(Mockito.any());

    this.sut.execute(
      BookingRoomInput.builder()
        .customerId(CUSTOMER_ID)
        .checkIn(LocalDate.now().minusDays(10))
        .checkOut(LocalDate.now().plusDays(5))
        .guests(TOTAL_GUESTS)
        .rooms(Set.of(
          BookRoomItemInput.builder()
            .roomId(ROOM_ID_1)
            .roomQuantity(1)
            .build(),
          BookRoomItemInput.builder()
            .roomId(ROOM_ID_2)
            .roomQuantity(1)
            .build()
        ))
        .build()
    );

    final var roomBookingRequestedEventArgumentCaptor = ArgumentCaptor.forClass(BookingRoomRequestedEvent.class);

    Mockito.verify(this.bookingRoomRequestedPublisher, Mockito.times(1))
      .publish(roomBookingRequestedEventArgumentCaptor.capture());
    Mockito.verify(this.customerBookingRoomUpdatePublisher, Mockito.times(1))
      .publish(Mockito.any());

    final var capturedEvent = roomBookingRequestedEventArgumentCaptor.getValue();

    Assertions.assertThat(capturedEvent)
      .satisfies(
        event -> Assertions.assertThat(event).isNotNull(),
        event -> Assertions.assertThat(event.getPrice()).isEqualByComparingTo(ROOM_TOTAL_PRICE),
        event -> Assertions.assertThat(event.getCustomerId()).isEqualTo(CUSTOMER_ID),
        event -> Assertions.assertThat(event.getGuests()).isEqualTo(TOTAL_GUESTS),
        event -> Assertions.assertThat(event.getRooms()).hasSize(TOTAL_ROOMS),
        event -> Assertions.assertThat(event.getReservationOrderId()).isNotNull()
      );
  }
}
