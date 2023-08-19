package com.hotel.booking.system.payment.service.application.configuration;

import com.hotel.booking.system.payment.service.core.application.mapper.PaymentUseCaseMapperImpl;
import com.hotel.booking.system.payment.service.core.application.messaging.PaymentRequestedHandlerImpl;
import com.hotel.booking.system.payment.service.core.application.usecase.PayOrderUseCaseMockImpl;
import com.hotel.booking.system.payment.service.core.ports.api.mapper.PaymentUseCaseMapper;
import com.hotel.booking.system.payment.service.core.ports.api.messaging.PaymentRequestedHandler;
import com.hotel.booking.system.payment.service.core.ports.api.usecase.PayOrderUseCase;
import com.hotel.booking.system.payment.service.core.ports.spi.messaging.publisher.PaymentResponsePublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentBeanConfiguration {

  @Bean
  public PaymentUseCaseMapper paymentUseCaseMapper() {
    return new PaymentUseCaseMapperImpl();
  }


  @Bean
  public PayOrderUseCase payOrderUseCase(final PaymentUseCaseMapper paymentUseCaseMapper) {
    return new PayOrderUseCaseMockImpl(
      paymentUseCaseMapper
    );
  }

  @Bean
  public PaymentRequestedHandler paymentRequestedHandler(
    final PayOrderUseCase payOrderUseCase,
    final PaymentUseCaseMapper paymentUseCaseMapper,
    final PaymentResponsePublisher paymentResponsePublisher
  ) {
    return new PaymentRequestedHandlerImpl(
      payOrderUseCase,
      paymentUseCaseMapper,
      paymentResponsePublisher
    );
  }

}
