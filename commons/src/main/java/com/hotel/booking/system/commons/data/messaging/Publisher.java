package com.hotel.booking.system.commons.data.messaging;

import com.hotel.booking.system.commons.core.domain.event.Event;

@FunctionalInterface
public interface Publisher<T extends Event> {

  void publish(T event);

}
