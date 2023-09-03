package com.hotel.booking.system.customer.service.core.domain.valueobject;

import com.hotel.booking.system.commons.core.message.ApplicationMessage;
import com.hotel.booking.system.customer.service.core.domain.exception.CustomerDomainException;

import java.text.MessageFormat;

public record Cpf(
  String value
) {

  private static final int CPF_SIZE = 11;
  private static final String CPF_TEMPLATE = "{0}.{1}.{2}-{3}";

  public Cpf {
    if (value == null) {
      throw new CustomerDomainException(ApplicationMessage.CUSTOMER_CPF_NOT_NULL);
    }
    if (value.length() != CPF_SIZE) {
      throw new CustomerDomainException(ApplicationMessage.CUSTOMER_CPF_INVALID);
    }
  }

  public String formatted() {
    final var firstGroup = this.value.substring(0, 3);
    final var secondGroup = this.value.substring(3, 6);
    final var thirdGroup = this.value.substring(6, 9);
    final var verifierDigit = this.value.substring(9, 11);
    return MessageFormat.format(
      CPF_TEMPLATE,
      firstGroup,
      secondGroup,
      thirdGroup,
      verifierDigit
    );
  }

}
