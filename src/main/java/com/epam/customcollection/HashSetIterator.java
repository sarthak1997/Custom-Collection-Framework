package com.epam.customcollection;

import java.util.Iterator;

public class HashSetIterator<E> implements Iterator<E> {

  private LinkedListNode<E>[] hashTable;
  private int currentIndex;
  private LinkedListNode<E> tempIterator;

  /**
   * Parameterized Constructor.
   */
  public HashSetIterator(LinkedListNode<E>[] hashTable) {
    this.hashTable = hashTable;
    currentIndex = 0;
    tempIterator = hashTable[currentIndex];
  }

  /**
   * method to check whether there is a next element or not.
   * @return boolean to indicate the result.
   */
  public boolean hasNext() {
    while (hashTable.length > currentIndex) {
      if (null == tempIterator) {
        currentIndex++;
        if (hashTable.length > currentIndex) {
          tempIterator = hashTable[currentIndex];
        } else {
          return false;
        }
      } else {
        return true;
      }
    }
    return false;
  }

  /**
   * Iterator method to fetch the next element in the set.
   */
  public E next() {

    E result = tempIterator.getElement();
    if (null == tempIterator.getNext()) {
      currentIndex++;
      tempIterator = hashTable[currentIndex];
    } else {
      tempIterator = tempIterator.getNext();
    }
    return result;
  }

}
