package com.epam.customcollection;

import java.util.Iterator;

public class CustomLinkedSetIterator<E> implements Iterator<E> {

  LinkedListNode<E> iterator;

  public CustomLinkedSetIterator(LinkedListNode<E> head) {
    super();
    this.iterator = head;
  }

  /**
   * Iterator method to check whether there is a next element in the set.
   */
  public boolean hasNext() {
    if (null != iterator) {
      return true;
    }
    return false;
  }

  /**
   * Iterator method to fetch the next element in the set.
   */
  public E next() {
    E result = iterator.getElement();
    iterator = iterator.getNext();
    return result;
  }

}
