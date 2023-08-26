package com.hotel.booking.system.booking.service.data.messaging.listener;

import com.hotel.booking.system.booking.service.core.ports.api.messaging.BookingRoomStatusChangedHandler;
import com.hotel.booking.system.booking.service.core.ports.spi.messaging.listener.BookingRoomStatusChangedListener;
import com.hotel.booking.system.commons.core.domain.event.BookingRoomStatusUpdatedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class BookingRoomStatusChangedRabbitMQListener implements BookingRoomStatusChangedListener {

  private final BookingRoomStatusChangedHandler handler;

  @Override
  @RabbitListener(queues = "${app.rabbitmq.queue.booking-room-status-changed}")
  public void listen(final BookingRoomStatusUpdatedEvent event) {
    log.info("BookingRoomStatusUpdatedEvent received: {}, ", event);
    this.handler.handle(event);
  }
}
