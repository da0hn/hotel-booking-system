package com.hotel.booking.system.booking.service.core.application.usecase;

import com.hotel.booking.system.booking.service.core.application.dto.UpdateBookingStatusInput;
import com.hotel.booking.system.booking.service.core.application.dto.UpdateBookingStatusOutput;
import com.hotel.booking.system.booking.service.core.domain.entity.Booking;
import com.hotel.booking.system.booking.service.core.domain.exception.BookingDomainException;
import com.hotel.booking.system.booking.service.core.ports.api.usecase.UpdateBookingStatusUseCase;
import com.hotel.booking.system.booking.service.core.ports.spi.repository.BookingRepository;
import com.hotel.booking.system.commons.core.domain.valueobject.ReservationOrderId;
import com.hotel.booking.system.commons.core.message.ApplicationMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UpdateBookingStatusUseCaseImpl implements UpdateBookingStatusUseCase {

  private final BookingRepository bookingRepository;

  public UpdateBookingStatusUseCaseImpl(final BookingRepository bookingRepository) {
    this.bookingRepository = bookingRepository;
  }

  @Override
  public UpdateBookingStatusOutput execute(final UpdateBookingStatusInput input) {
    final var booking = this.findBookingBy(input.reservationOrderId());
    booking.changeStatusTo(input.status());
    this.bookingRepository.save(booking);
    return new UpdateBookingStatusOutput();
  }

  private Booking findBookingBy(final ReservationOrderId reservationOrderId) {
    return this.bookingRepository.findBookingByReservationOrderId(reservationOrderId)
      .orElseThrow(() -> new BookingDomainException(ApplicationMessage.BOOKING_NOT_FOUND));
  }
}
