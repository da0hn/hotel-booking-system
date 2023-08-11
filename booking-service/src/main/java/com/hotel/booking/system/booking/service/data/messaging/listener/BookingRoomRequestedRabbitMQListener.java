package com.hotel.booking.system.booking.service.data.messaging.listener;

import com.hotel.booking.system.booking.service.core.ports.api.messaging.BookingRoomRequestedHandler;
import com.hotel.booking.system.booking.service.core.ports.spi.messaging.listener.BookingRoomRequestedListener;
import com.hotel.booking.system.commons.core.domain.event.BookingRoomRequestedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class BookingRoomRequestedRabbitMQListener implements BookingRoomRequestedListener {

  private final BookingRoomRequestedHandler handler;


  @RabbitListener(queues = "${app.rabbitmq.queue.booking-room-requested}")
  public void listen(final BookingRoomRequestedEvent event) {
    log.info(
      "BookingRoomRequestedEvent received {}",
      event
    );
    this.handler.handle(event);
  }

}
