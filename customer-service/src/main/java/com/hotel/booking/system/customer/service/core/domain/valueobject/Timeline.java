package com.hotel.booking.system.customer.service.core.domain.valueobject;

import com.hotel.booking.system.commons.core.domain.AbstractDomainList;
import com.hotel.booking.system.customer.service.core.domain.entity.ReservationOrderTimeline;

import java.util.ArrayList;
import java.util.List;

public class Timeline extends AbstractDomainList<ReservationOrderTimeline> {
  protected Timeline(final List<ReservationOrderTimeline> data) {
    super(data);
  }

  public static Timeline empty() {
    return new Timeline(new ArrayList<>());
  }

  public static Timeline of(final List<ReservationOrderTimeline> items) {
    return new Timeline(items);
  }
}
