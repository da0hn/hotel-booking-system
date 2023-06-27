package com.hotel.booking.system.commons.application.dto;

import java.util.Collection;

public interface CollectionResponse<T> extends Response<Collection<T>> {

  Collection<T> getData();

  boolean getSuccess();

}
