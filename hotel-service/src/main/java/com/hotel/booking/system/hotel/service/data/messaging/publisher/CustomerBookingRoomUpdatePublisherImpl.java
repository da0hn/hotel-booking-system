package com.hotel.booking.system.hotel.service.data.messaging.publisher;

import com.hotel.booking.system.hotel.service.core.domain.event.BookingRoomInitiatedEvent;
import com.hotel.booking.system.hotel.service.core.ports.spi.messaging.CustomerBookingRoomUpdatePublisher;
import org.springframework.stereotype.Component;

@Component
public class CustomerBookingRoomUpdatePublisherImpl implements CustomerBookingRoomUpdatePublisher {

  @Override
  public void publish(final BookingRoomInitiatedEvent event) {
  }
}
