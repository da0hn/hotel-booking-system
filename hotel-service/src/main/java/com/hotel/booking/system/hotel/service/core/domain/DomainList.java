package com.hotel.booking.system.hotel.service.core.domain;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@FunctionalInterface
public interface DomainList<T> extends List<T> {

  List<T> data();

  @Override
  default int size() {
    return this.data().size();
  }

  @Override
  default boolean isEmpty() {
    return this.data().isEmpty();
  }

  @Override
  default boolean contains(final Object o) {
    return this.data().contains(o);
  }

  @Override
  default Iterator<T> iterator() {
    return this.data().iterator();
  }

  @Override
  default Object[] toArray() {
    return this.data().toArray();
  }

  @Override
  default <T1> T1[] toArray(final T1[] a) {
    return this.data().toArray(a);
  }

  @Override
  default boolean add(final T t) {
    return this.data().add(t);
  }

  @Override
  default boolean remove(final Object o) {
    return this.data().remove(o);
  }

  @Override
  default boolean containsAll(final Collection<?> c) {
    return this.data().containsAll(c);
  }

  @Override
  default boolean addAll(final Collection<? extends T> c) {
    return this.data().addAll(c);
  }

  @Override
  default boolean addAll(final int index, final Collection<? extends T> c) {
    return this.data().addAll(index, c);
  }

  @Override
  default boolean removeAll(final Collection<?> c) {
    return this.data().removeAll(c);
  }

  @Override
  default boolean retainAll(final Collection<?> c) {
    return this.data().retainAll(c);
  }

  @Override
  default void clear() {
    this.data().clear();
  }

  @Override
  boolean equals(final Object o);

  @Override
  int hashCode();

  @Override
  default T get(final int index) {
    return this.data().get(index);
  }

  @Override
  default T set(final int index, final T element) {
    return this.data().set(index, element);
  }

  @Override
  default void add(final int index, final T element) {
    this.data().add(index, element);
  }

  @Override
  default T remove(final int index) {
    return this.data().remove(index);
  }

  @Override
  default int indexOf(final Object o) {
    return this.data().indexOf(o);
  }

  @Override
  default int lastIndexOf(final Object o) {
    return this.data().lastIndexOf(o);
  }

  @Override
  default ListIterator<T> listIterator() {
    return this.data().listIterator();
  }

  @Override
  default ListIterator<T> listIterator(final int index) {
    return this.data().listIterator(index);
  }

  @Override
  default List<T> subList(final int fromIndex, final int toIndex) {
    return this.data().subList(fromIndex, toIndex);
  }
}
