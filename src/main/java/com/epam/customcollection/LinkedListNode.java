package com.epam.customcollection;

public class LinkedListNode<T> {

  T element;
  LinkedListNode<T> next;

  /**
   * Parameterized Constructor to initialize the parameters of Node.
   * @param element the data going to be stored inside the node
   * @param next reference to the next Node.
   */
  public LinkedListNode(T element, LinkedListNode<T> next) {
    super();
    this.element = element;
    this.next = next;
  }

  public T getElement() {
    return element;
  }

  public void setElement(Object element) {
    this.element = (T) element;
  }

  public LinkedListNode<T> getNext() {
    return next;
  }

  public void setNext(LinkedListNode<T> next) {
    this.next = next;
  }

}
