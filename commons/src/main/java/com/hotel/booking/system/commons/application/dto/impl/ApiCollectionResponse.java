package com.hotel.booking.system.commons.application.dto.impl;


import com.hotel.booking.system.commons.application.dto.CollectionResponse;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@AllArgsConstructor
public class ApiCollectionResponse<T> implements CollectionResponse<T> {

  private final Collection<T> data;
  private final boolean success;

  public static <T> CollectionResponse<T> of(final Collection<T> data) {
    return new ApiCollectionResponse<>(
      data,
      true
    );
  }

  @Override
  public Collection<T> getData() {
    return Optional.ofNullable(this.data)
      .map(Collections::unmodifiableCollection)
      .orElseGet(ArrayList::new);
  }

  @Override
  public boolean getSuccess() {
    return this.success;
  }

}
