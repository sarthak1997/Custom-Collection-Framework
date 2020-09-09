package com.epam.customcollection.graph;

import com.epam.exceptions.ElementNotFoundException;

public interface Graph<E> {

  /**
   * Method to add element inside the graph.
   * @param element that is going to be added inside the graph
   * @return boolean to indicate the success or failure of the result.
   */
  public boolean add(E element);

  /**
   * Method to add edge between two nodes in the graph.
   * @param element1 the first node of the graph
   * @param element2 the second node of the graph
   * @return {@code boolean} to indicate the success of the operation
   * @throws ElementNotFoundException if the node element is not found in the graph.
   * 
   */
  public boolean addEdge(E element1, E element2) throws ElementNotFoundException;

  /**
   * Method to remove a node from the graph.
   * @param element the node needs to be removed
   * @throws ElementNotFoundException if the node element is not found in the graph.
   */
  public void remove(E element) throws ElementNotFoundException;

  /**
   * Method to remove edge between two nodes in the graph.
   * @param element1 the first node element
   * @param element2 the second node element
   * @return {@code boolean } to indicate the success or failure of the operation.
   * @throws ElementNotFoundException if the node element is not found in the graph.
   */
  public boolean removeEdge(E element1, E element2) throws ElementNotFoundException;

}
