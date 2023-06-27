package com.hotel.booking.system.hotel.service.core.domain.valueobject;

import com.hotel.booking.system.commons.core.message.ApplicationMessage;
import com.hotel.booking.system.hotel.service.core.domain.exception.HotelDomainException;
import io.micrometer.common.util.StringUtils;
import lombok.Builder;

@Builder
public class HotelAddress {

  private static final String CEP_PATTERN = "^\\d{5}-\\d{3}$";
  private final String cep;
  private final String street;

  public HotelAddress(final String cep, final String street) {
    this.cep = cep;
    this.street = street;
  }

  public void validate() {
    if (StringUtils.isBlank(this.street)) {
      throw new HotelDomainException(ApplicationMessage.HOTEL_STREET_INVALID);
    }
    if (StringUtils.isBlank(this.cep) || !this.cep.matches(CEP_PATTERN)) {
      throw new HotelDomainException(ApplicationMessage.HOTEL_CEP_INVALID);
    }
  }

  public String getCep() {
    return this.cep;
  }

  public String getStreet() {
    return this.street;
  }
}
