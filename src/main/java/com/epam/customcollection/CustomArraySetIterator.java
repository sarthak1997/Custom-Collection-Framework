package com.epam.customcollection;

import java.util.Iterator;

public class CustomArraySetIterator<E> implements Iterator<E> {

  Object[] listObj;
  int currentIndex;
  int size;
  
  /**
   * Parameterized Constructor for initializing the set with size.
   */
  public CustomArraySetIterator(Object[] listObj, int size) {
    this.listObj = listObj;
    currentIndex = 0;
    this.size = size;
  }

  /**
   * method to check whether there is a next element or not.
   * @return boolean to indicate the result.
   */
  public boolean hasNext() {

    if (size > currentIndex && null != listObj[currentIndex]) {
      return true;
    }

    return false;
  }

  /**
   * method to fetch the next element of the iterator.
   * @return the next element of the iterator  
   */
  public E next() {

    @SuppressWarnings("unchecked")
    E result = (E) listObj[currentIndex];
    currentIndex = currentIndex + 1;

    return result;
  }

}
