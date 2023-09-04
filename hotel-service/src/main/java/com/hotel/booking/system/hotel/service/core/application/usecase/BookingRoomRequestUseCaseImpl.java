package com.hotel.booking.system.hotel.service.core.application.usecase;

import com.hotel.booking.system.commons.core.domain.valueobject.Money;
import com.hotel.booking.system.commons.core.domain.valueobject.ReservationOrderId;
import com.hotel.booking.system.commons.core.domain.valueobject.RoomId;
import com.hotel.booking.system.commons.core.message.ApplicationMessage;
import com.hotel.booking.system.hotel.service.core.application.dto.BookRoomItemInput;
import com.hotel.booking.system.hotel.service.core.application.dto.BookingRoomInput;
import com.hotel.booking.system.hotel.service.core.application.dto.BookingRoomOutput;
import com.hotel.booking.system.hotel.service.core.domain.entity.Room;
import com.hotel.booking.system.hotel.service.core.domain.entity.Rooms;
import com.hotel.booking.system.hotel.service.core.domain.exception.HotelDomainException;
import com.hotel.booking.system.hotel.service.core.ports.api.usecase.BookingRoomRequestUseCase;
import com.hotel.booking.system.hotel.service.core.ports.spi.messaging.publisher.BookingRoomRequestedPublisher;
import com.hotel.booking.system.hotel.service.core.ports.spi.messaging.publisher.CustomerBookingRoomStatusUpdatedPublisher;
import com.hotel.booking.system.hotel.service.core.ports.spi.repository.HotelRepository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class BookingRoomRequestUseCaseImpl implements BookingRoomRequestUseCase {

  private static final BinaryOperator<Integer> BINARY_FUNCTION_IDENTITY = (currentValue, newValue) -> currentValue;
  private final HotelRepository hotelRepository;
  private final CustomerBookingRoomStatusUpdatedPublisher customerBookingRoomUpdatePublisher;
  private final BookingRoomRequestedPublisher bookingRoomRequestedPublisher;

  public BookingRoomRequestUseCaseImpl(
    final HotelRepository hotelRepository,
    final CustomerBookingRoomStatusUpdatedPublisher customerBookingRoomUpdatePublisher,
    final BookingRoomRequestedPublisher bookingRoomRequestedPublisher
  ) {
    this.customerBookingRoomUpdatePublisher = customerBookingRoomUpdatePublisher;
    this.bookingRoomRequestedPublisher = bookingRoomRequestedPublisher;
    this.hotelRepository = hotelRepository;
  }

  private List<RoomId> mapToRoomIds(final BookingRoomInput input) {
    return input.rooms().stream()
      .map(BookRoomItemInput::roomId)
      .map(RoomId::of)
      .collect(Collectors.toList());
  }

  @Override
  public BookingRoomOutput execute(final BookingRoomInput input) {

    final var rooms = this.hotelRepository.findAllRoomsById(this.mapToRoomIds(input));
    this.validateRooms(input.rooms(), rooms);
    this.validateGuest(input, rooms);
    // TODO: this.validateCustomerId(input.customerId());
    final var reservationOrderId = ReservationOrderId.newInstance();

//    this.bookingRoomRequestedPublisher.publish(
//      BookingRoomRequestedEvent.builder()
//        .reservationOrderId(reservationOrderId.getValue().toString())
//        .customerId(input.customerId())
//        .guests(input.guests())
//        .price(this.getTotalPrice(input.rooms(), rooms).getValue())
//        .checkIn(input.checkIn())
//        .checkOut(input.checkOut())
//        .rooms(
//          input.rooms().stream()
//            .map(r ->
//              BookingRoomItemRepresentation.builder()
//                .roomId(r.roomId())
//                .quantity(r.roomQuantity())
//                .price(this.getItemPrice(r, rooms).getValue())
//                .build()
//            )
//            .collect(Collectors.toList())
//        )
//        .build()
//    );
//    this.customerBookingRoomUpdatePublisher.publish(
//      CustomerBookingInitiatedEvent.builder()
//        .customerId(input.customerId())
//        .reservationOrderId(reservationOrderId.getValue().toString())
//        .hotelId(input.hotelId())
//        .guests(input.guests())
//        .totalPrice(this.getTotalPrice(input.rooms(), rooms).getValue())
//        .checkIn(input.checkIn())
//        .checkOut(input.checkOut())
//        .status(CustomerReservationStatus.AWAITING_RESERVATION)
//        .rooms(
//          input.rooms().stream()
//            .map(r -> BookingRoomItemRepresentation.builder()
//              .roomId(r.roomId())
//              .quantity(r.roomQuantity())
//              .price(this.getItemPrice(r, rooms).getValue())
//              .build()
//            )
//            .collect(Collectors.toList())
//        )
//        .build()
//    );
    return new BookingRoomOutput(reservationOrderId.getValue());
  }

  private Money getItemPrice(final BookRoomItemInput room, final Collection<? extends Room> rooms) {
    return rooms.stream()
      .filter(r -> r.getId().getValue().toString().equals(room.roomId()))
      .map(Room::getCurrentPrice)
      .findFirst()
      .orElseThrow(() -> new HotelDomainException(ApplicationMessage.HOTEL_ROOM_NOT_FOUND));
  }

  private void validateGuest(final BookingRoomInput input, final Collection<? extends Room> rooms) {
    final var guests = input.guests();
    final var totalRoomCapacity = rooms.stream()
      .mapToInt(room -> room.getCapacity() * room.getQuantity())
      .sum();
    if (guests > totalRoomCapacity) {
      throw new HotelDomainException(ApplicationMessage.HOTEL_BOOKING_GUESTS_EXCEEDED);
    }
  }

  private Money getTotalPrice(final Collection<BookRoomItemInput> input, final Collection<? extends Room> rooms) {
    final var quantityByRoomId = this.groupQuantityByRoomId(input);
    return rooms.stream()
      .map(room -> {
        final var quantity = quantityByRoomId.get(room.getId());
        return room.getCurrentPrice().multiply(BigDecimal.valueOf(quantity));
      })
      .reduce(Money.ZERO, Money::add);
  }

  private void validateRooms(final Collection<BookRoomItemInput> input, final Rooms rooms) {
    final var roomsGroupedById = this.groupQuantityByRoomId(input);

    final var roomIds = input.stream()
      .map(BookRoomItemInput::roomId)
      .map(RoomId::of)
      .toList();

    final var hasUnknownRoom = roomIds.stream()
      .anyMatch(rooms::notContainsId);

    if (hasUnknownRoom) {
      throw new HotelDomainException(ApplicationMessage.HOTEL_ROOM_NOT_FOUND);
    }

    for (final Room room : rooms) {
      final var maybeRoomCapacity = Optional.ofNullable(roomsGroupedById.getOrDefault(
        room.getId(),
        null
      ));
      if (maybeRoomCapacity.isEmpty()) {
        throw new HotelDomainException(ApplicationMessage.HOTEL_ROOM_NOT_FOUND);
      }
      if (!room.hasCapacityFor(maybeRoomCapacity.get())) {
        throw new HotelDomainException(ApplicationMessage.HOTEL_ROOM_CAPACITY_EXCEEDED);
      }

    }
  }

  private Map<RoomId, Integer> groupQuantityByRoomId(final Collection<BookRoomItemInput> input) {
    return input.stream()
      .collect(Collectors.toMap(
        room -> RoomId.of(room.roomId()),
        BookRoomItemInput::roomQuantity,
        BINARY_FUNCTION_IDENTITY
      ));
  }
}
