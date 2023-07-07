package com.hotel.booking.system.hotel.service.data.messaging.publisher;

import com.hotel.booking.system.hotel.service.core.domain.event.BookingRoomRequestedEvent;
import com.hotel.booking.system.hotel.service.core.ports.spi.messaging.BookingRoomRequestedPublisher;
import org.springframework.stereotype.Component;

@Component
public class BookingRoomRequestedPublisherImpl implements BookingRoomRequestedPublisher {


  @Override
  public void publish(final BookingRoomRequestedEvent event) {
  }

}
