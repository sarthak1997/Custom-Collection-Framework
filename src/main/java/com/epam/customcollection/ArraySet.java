package com.epam.customcollection;

import com.epam.exceptions.DuplicateEntryException;
import com.epam.exceptions.ElementNotFoundException;
import com.epam.exceptions.NullNotAllowedException;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Custom Set Collection with generic nature User need to override it's equals
 * and compare method of {@code Comparator<Object>} in case of custom object It
 * uses a dynamic array by default size of 10 such that when it reaches it's
 * capacity the capacity gets doubled and when the size reaches half if the
 * capacity it shrinks down to half the size.
 * 
 */
public class ArraySet<T> implements Set<T>, Iterable<T> {

  private static final String INDEX_OUT_OF_BOUNDS_EXCEPTION_MESSAGE 
      = "SetIndexOutOfBoundException : Invalid Index - ";

  private static final int DEFAULT_CAPACITY = 2;

  // Main Dynamic Array
  private Object[] listObj;
  // Temporary array for grow and shrink of the main array
  private Object[] tempArr;
  private int capacity = DEFAULT_CAPACITY;
  private int size;

  // default initialization
  public ArraySet() {
    listObj = new Object[DEFAULT_CAPACITY];
    size = 0;
  }

  /**
   * initialization of set with a particular size.
   * 
   * @param capacity
   *          to initialize array set with given capacity.
   */
  public ArraySet(int capacity) {
    this.capacity = capacity;
    listObj = new Object[capacity];
    size = 0;
  }

  /**
   * initialization with a pre-built collection.
   * 
   * @param init
   *          to initialize this collection with another collection.
   * 
   * @throws DuplicateEntryException
   *           to avoid duplicate elements in the set.
   */
  public ArraySet(Collection<T> init) throws DuplicateEntryException {
    this(init.size());

    for (T var : init) {
      this.add(var);
    }
  }

  /**
   * to get element present at an index in the set.
   * 
   * @param index
   *          to get the element at particular index.
   * 
   * @return {@code T} for the element present at the index.
   * @throws IndexOutOfBoundsException
   *           if the index is invalid.
   */
  public T get(int index) throws IndexOutOfBoundsException {
    if (0 > index || size <= index) {
      throw new IndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS_EXCEPTION_MESSAGE + index);
    }
    return (T) listObj[index];
  }

  /**
   * to add element in the set.
   * 
   * @param element
   *          of template type T to add into the set.
   */
  public void add(T element) throws DuplicateEntryException {
    if (null == element) {
      throw new NullNotAllowedException("Adding null value to the set is not allowed");
    }

    if (this.contains(element)) {

      throw new DuplicateEntryException("DuplicateEntryException :" 
          + " Duplicate elements are not allowed!");
    }

    if (size == capacity) {
      tempArr = new Object[2 * capacity];
      System.arraycopy(listObj, 0, tempArr, 0, size);
      listObj = null;
      listObj = tempArr;
      capacity = 2 * capacity;
    }

    listObj[size] = element;
    size += 1;
  }

  /**
   * update in set to basically remove the old object and add the new object in
   * the set more of a replace.
   * 
   * @param oldObject
   *          - object to get updated
   * @param newObject
   *          - the new object with updated values
   */
  public void update(T oldObject, T newObject) throws ElementNotFoundException,
       DuplicateEntryException {

    if (null == oldObject || null == newObject) {
      throw new NullNotAllowedException("Null value in the set is not allowed");
    }

    if (this.contains(newObject)) {
      throw new DuplicateEntryException(
          "The new element you are putting for update" + " is already present in the set!");
    }
    for (int i = 0; size > i; i++) {
      @SuppressWarnings("unchecked")
      T tempVar = (T) listObj[i];
      if (tempVar.equals(oldObject)) {
        listObj[i] = newObject;
        return;
      }
    }
  }

  /**
   * to update at particular index.
   * 
   * @param index
   *          to update the collection at a particular index.
   * @param element
   *          to update the existing element with the current one
   * @throws DuplicateEntryException
   *           to avoid similar elements in the set.
   */
  public void update(int index, T element) throws DuplicateEntryException {
    if (null == element) {
      throw new NullNotAllowedException("Null value in the set is not allowed");
    }

    if (0 > index || size <= index) {
      throw new IndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS_EXCEPTION_MESSAGE + index);
    }

    if (this.contains(element)) {
      throw new DuplicateEntryException("DuplicateEntryException :" + " Duplicate elements are not allowed!");
    }

    listObj[index] = element;
  }

  /**
   * to remove at particular index.
   * 
   * @param index
   *          to remove element at the given param.
   */
  public void remove(int index) {

    if (0 > index || size <= index) {

      throw new IndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS_EXCEPTION_MESSAGE + index);
    }

    for (int i = index; size - 1 > i; i++) {
      listObj[i] = listObj[i + 1];
    }
    size = size - 1;

    if (size < (capacity / 2)) {
      tempArr = new Object[capacity / 2];
      System.arraycopy(listObj, 0, tempArr, 0, size);
      listObj = null;
      listObj = tempArr;
      capacity = capacity / 2;
    }

  }

  /**
   * to remove a particular element.
   * 
   * @param element
   *          for removing element from the set.
   */
  public void remove(T element) throws ElementNotFoundException {
    if (null == element) {
      throw new NullNotAllowedException("Null value in the set is not present");
    }

    if (this.contains(element)) {
      for (int i = 0; size > i; i++) {
        if (listObj[i].equals(element)) {
          this.remove(i);
          return;
        }
      }
    } else {
      throw new ElementNotFoundException("InvalidSetElementException : Element does not exists");
    }
  }

  /**
   * to find index of a particular element.
   * 
   * @param element
   *          as a parameter.
   * @return index of the passed element.
   */
  public int indexOf(T element) {

    int result = -1;

    if (null == element) {
      throw new NullNotAllowedException("Null value in the set is not present");
    }

    if (this.contains(element)) {
      for (int i = 0; size > i; i++) {
        if (listObj[i].equals(element)) {
          result = i;
          break;
        }
      }
    }
    return result;
  }

  /**
   * return true when an element is present in the set.
   * 
   * @param element
   *          as a parameter
   * @return {@code boolean} to indicate whether is element is present or not
   */
  @SuppressWarnings("unchecked")
  private boolean contains(T element) {
    boolean retValue = false;
    for (int i = 0; size > i; i++) {
      T tempVar = (T) listObj[i];

      if (tempVar.equals(element)) {
        retValue = true;
        break;
      }
    }
    return retValue;
  }

  /**
   * to make it iterable.
   * 
   * @return {@link Iterator}
   */
  public Iterator<T> iterator() {
    return new CustomArraySetIterator<>(listObj, size);
  }

  /**
   * to find through nay parameter of the custom object using lambda expressions.
   * 
   * @param resolver
   *          to render the lambda expression passed.
   * @return {@link ArraySet} of all the matched results.
   */
  @SuppressWarnings("unchecked")
  public ArraySet<T> find(FindResolver<T> resolver) {
    int[] resultIndex = new int[size];
    int resultSize = 0;

    for (int i = 0; size > i; i++) {
      if (resolver.resolve((T) listObj[i])) {
        resultIndex[i] = i;
        resultSize = resultSize + 1;
      } else {
        resultIndex[i] = -1;
      }
    }

    ArraySet<T> resultSet = new ArraySet<>();

    for (int i = 0; size > i; i++) {
      if (-1 != resultIndex[i]) {
        try {
          resultSet.add((T) listObj[resultIndex[i]]);
        } catch (DuplicateEntryException exc) {
          exc.printStackTrace();
        }
      }
    }
    return resultSet;
  }

  /**
   * static method to sort with the custom set object provided and the user
   * defined comparator heap sort is used for sorting.
   * 
   * @param setObj
   *          is the set required for sorting
   * @param comp
   *          {@link Comparator} so that defining the basis of the sorting
   */
  public static void sort(ArraySet<?> setObj, Comparator<Object> comp) {

    int tempSize = setObj.size;
    setObj.buildHeap(comp);
    Object[] temp = new Object[tempSize];
    for (int i = tempSize - 1; 0 <= i; i--) {
      temp[i] = setObj.extractMax(comp);
    }

    for (int i = 0; tempSize > i; i++) {
      setObj.listObj[i] = temp[i];
    }

    setObj.setSize(tempSize);
  }

  /**
   * method for heap sort to reset size of the main array as during extract max
   * operation it decreases.
   * 
   * @param tempSize
   *          to set the size of the set explicitly
   */
  private void setSize(int tempSize) {
    this.size = tempSize;
  }

  // extractMax operation of Max-Heap
  private Object extractMax(Comparator<Object> comp) {
    final Object result = listObj[0];
    this.swap(0, size - 1);
    size = size - 1;
    this.shiftDown(0, comp);
    return result;
  }

  // to build the heap by giving any array as an argument
  private void buildHeap(Comparator<Object> comp) {

    for (int i = (size / 2); 0 <= i; i--) {
      this.shiftDown(i, comp);
    }

  }

  // method to organize the heap in case of any operation
  private void shiftDown(int index, Comparator<Object> comp) {

    int maxIndex = index;

    int left = this.leftChild(index);

    if (size > left && 0 < comp.compare(this.listObj[left], this.listObj[maxIndex])) {
      maxIndex = left;
    }

    int right = this.rightChild(index);

    if (size > right && 0 < comp.compare(this.listObj[right], this.listObj[maxIndex])) {
      maxIndex = right;
    }

    if (index != maxIndex) {
      this.swap(index, maxIndex);
      this.shiftDown(maxIndex, comp);
    }
  }

  // to get index of right child in the heap
  private int rightChild(int index) {
    return 2 * index + 2;
  }

  // to get index of left child in the heap
  private int leftChild(int index) {
    return 2 * index + 1;
  }

  // to swap two index objects
  private void swap(int index1, int index2) {

    Object temp = listObj[index1];
    listObj[index1] = listObj[index2];
    listObj[index2] = temp;
  }

  /**
   * toString to print content of the set.
   * 
   */
  @Override
  public String toString() {

    StringBuilder result = new StringBuilder("Set = [-");

    for (int i = 0; size > i; i++) {
      result.append(listObj[i] + "-");
    }
    result.append(']');

    return result.toString();
  }

  /**
   * to get current size of the set.
   * 
   * @return {@code int} for the size
   */
  public int size() {
    return size;
  }

}
