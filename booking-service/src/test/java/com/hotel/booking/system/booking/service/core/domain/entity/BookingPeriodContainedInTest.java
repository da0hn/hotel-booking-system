package com.hotel.booking.system.booking.service.core.domain.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.function.UnaryOperator;

@Tag("unit")
@DisplayName("Period comparison with another Period tests")
@ExtendWith(MockitoExtension.class)
class BookingPeriodContainedInTest {

  private static final LocalDate INITIAL_DATE = LocalDate.now().minusDays(10);
  private static final LocalDate END_DATE = LocalDate.now().plusDays(5);

  @Test
  @DisplayName("Should return true when current period is contained in other period")
  void test1() {
    final var sut = BookingPeriod.of(
      INITIAL_DATE,
      END_DATE
    );

    final var response = sut.isContainedIn(
      BookingPeriod.of(
        this.modify(INITIAL_DATE, date -> date.minusDays(1)),
        this.modify(END_DATE, date -> date.plusDays(1))
      )
    );

    Assertions.assertThat(response).isTrue();
  }

  @Test
  @DisplayName("Should return true when current check-in is contained in other period")
  void test2() {
    final var sut = BookingPeriod.of(
      INITIAL_DATE,
      END_DATE
    );

    final var response = sut.isContainedIn(
      BookingPeriod.of(
        this.modify(INITIAL_DATE, date -> date.minusDays(2)),
        this.modify(END_DATE, UnaryOperator.identity())
      )
    );

    Assertions.assertThat(response).isTrue();
  }

  @Test
  @DisplayName("Should return true when current check-out is contained in other period")
  void test3() {
    final var sut = BookingPeriod.of(
      INITIAL_DATE,
      END_DATE
    );

    final var response = sut.isContainedIn(
      BookingPeriod.of(
        this.modify(INITIAL_DATE, UnaryOperator.identity()),
        this.modify(END_DATE, date -> date.plusDays(2))
      )
    );

    Assertions.assertThat(response).isTrue();
  }


  @Test
  @DisplayName("Should return false when current period is totally after other period")
  void test4() {
    final var sut = BookingPeriod.of(
      INITIAL_DATE,
      END_DATE
    );

    final var response = sut.isContainedIn(
      BookingPeriod.of(
        this.modify(END_DATE, date -> date.plusDays(5)),
        this.modify(END_DATE, date -> date.plusDays(10))
      )
    );

    Assertions.assertThat(response).isFalse();
  }


  @Test
  @DisplayName("Should return false when current period is totally before other period")
  void test5() {
    final var sut = BookingPeriod.of(
      INITIAL_DATE,
      END_DATE
    );

    final var response = sut.isContainedIn(
      BookingPeriod.of(
        this.modify(INITIAL_DATE, date -> date.minusDays(10)),
        this.modify(INITIAL_DATE, date -> date.minusDays(5))
      )
    );

    Assertions.assertThat(response).isFalse();
  }


  @Test
  @DisplayName("Should return true when current check-in is equal to other check-in")
  void test6() {
    final var sut = BookingPeriod.of(
      INITIAL_DATE,
      END_DATE
    );

    final var response = sut.isContainedIn(
      BookingPeriod.of(
        this.modify(INITIAL_DATE, UnaryOperator.identity()),
        this.modify(END_DATE, date -> date.plusDays(1))
      )
    );

    Assertions.assertThat(response).isTrue();
  }

  @Test
  @DisplayName("Should return true when current check-out is equal to other check-out")
  void test7() {
    final var sut = BookingPeriod.of(
      INITIAL_DATE,
      END_DATE
    );

    final var response = sut.isContainedIn(
      BookingPeriod.of(
        this.modify(INITIAL_DATE, date -> date.plusDays(1)),
        this.modify(END_DATE, UnaryOperator.identity())
      )
    );

    Assertions.assertThat(response).isTrue();
  }

  @Test
  @DisplayName("Should return true when current period contains other period")
  void test8() {
    final var sut = BookingPeriod.of(
      INITIAL_DATE,
      END_DATE
    );

    final var response = sut.isContainedIn(
      BookingPeriod.of(
        this.modify(INITIAL_DATE, other -> other.plusDays(1)),
        this.modify(END_DATE, other -> other.minusDays(1))
      )
    );

    Assertions.assertThat(response).isTrue();
  }

  private LocalDate modify(final LocalDate date, final UnaryOperator<LocalDate> operation) {
    return operation.apply(date);
  }
}
