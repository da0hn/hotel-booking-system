package com.hotel.booking.system.commons.core.domain.valueobject;

import com.hotel.booking.system.commons.core.domain.AbstractDomainList;

import java.util.ArrayList;
import java.util.List;

public class FailureMessages extends AbstractDomainList<String> {

  private FailureMessages(final List<String> data) {
    super(data);
  }

  public static FailureMessages empty() {
    return new FailureMessages(new ArrayList<>());
  }

  public static FailureMessages newInstance(final List<String> messages) {
    return new FailureMessages(messages);
  }

  public static FailureMessages of(final String... messages) {
    return new FailureMessages(List.of(messages));
  }


  public boolean isNotEmpty() {
    return !this.isEmpty();
  }
}
