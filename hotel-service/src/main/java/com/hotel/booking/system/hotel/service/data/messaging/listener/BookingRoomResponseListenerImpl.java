package com.hotel.booking.system.hotel.service.data.messaging.listener;

import com.hotel.booking.system.commons.core.domain.event.BookingRoomResponseEvent;
import com.hotel.booking.system.hotel.service.core.ports.api.messaging.BookingRoomResponseHandler;
import com.hotel.booking.system.hotel.service.core.ports.spi.messaging.listener.BookingRoomResponseListener;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class BookingRoomResponseListenerImpl implements BookingRoomResponseListener {

  private final BookingRoomResponseHandler handler;

  @Override
  @RabbitListener(queues = "${app.rabbitmq.queue.booking-room-confirmation}")
  public void listen(final BookingRoomResponseEvent event) {
    log.info("BookingRoomResponseEvent received: {}, ", event);
    this.handler.handle(event);
  }

}
