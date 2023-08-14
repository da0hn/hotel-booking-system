package com.hotel.booking.system.hotel.service.core.application.messaging;

import com.hotel.booking.system.commons.core.domain.event.BookingRoomFailedEvent;
import com.hotel.booking.system.commons.core.domain.event.BookingRoomPendingEvent;
import com.hotel.booking.system.commons.core.domain.event.BookingRoomResponseEvent;
import com.hotel.booking.system.hotel.service.core.ports.api.messaging.BookingRoomResponseHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BookingRoomResponseHandlerImpl implements BookingRoomResponseHandler {


  @Override
  public void handle(final BookingRoomResponseEvent event) {
    switch (event) {
      case final BookingRoomPendingEvent e -> {
        // TODO: enviar mensagem para o serviço do cliente (customer-service)
        log.info("Booking room pending, notifying customer service | reservationOrderId={}", e.getReservationOrderId());
        // TODO: enviar mensagem para o serviço de pagamento (payment-service)
        log.info("Booking room pending, requesting for payment | reservationOrderId={}", e.getReservationOrderId());
      }
      case final BookingRoomFailedEvent e -> {
        log.info(
          "Booking room failed with error messages \"{}\". Notifying customer service | reservationOrderId={}",
          e.getReservationOrderId(),
          String.join(", ")
        );
        // TODO:"enviar mensagem para o serviço do cliente (customer-service)
      }
      default -> throw new IllegalStateException(
        "Failed on handling sub-type of BookingRoomResponseEvent: Unknown event"
      );
    }
  }

}
