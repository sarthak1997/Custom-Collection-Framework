package com.epam.customcollection.graph;

import com.epam.exceptions.ElementNotFoundException;
import com.epam.exceptions.NullNotAllowedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class DirectedGraph<E> implements Graph<E> {

  ArrayList<HashSet<Integer>> adjList;
  HashMap<E, Integer> nodeMap;
  int currentIndex;

  /**
   * Constructor to initialize the attributes with default values.
   */
  public DirectedGraph() {
    adjList = new ArrayList<>();
    nodeMap = new HashMap<>();
    currentIndex = 0;
  }

  @Override
  public boolean add(E element) {

    if (null == element) {
      throw new NullNotAllowedException("Null elements not allowed in graph");
    }

    if (nodeMap.containsKey(element)) {
      return false;
    }
    nodeMap.put(element, currentIndex);
    adjList.add(new HashSet<>());
    currentIndex += 1;

    return true;
  }

  @Override
  public boolean addEdge(E element1, E element2) throws ElementNotFoundException {

    if (null == element1 || null == element2) {
      throw new NullNotAllowedException("Null elements not present in graph");
    }

    Integer index1 = nodeMap.get(element1);
    Integer index2 = nodeMap.get(element2);

    if (null == index1 || null == index2) {
      throw new ElementNotFoundException("Elements not present in graph");
    }

    if (adjList.get(index1).contains(index2)) {
      return false;
    }

    adjList.get(index1).add(index2);

    return true;
  }

  @Override
  public void remove(E element) throws ElementNotFoundException {
    if (null == element) {
      throw new NullNotAllowedException("Null elements not present in graph");
    }

    Integer index = nodeMap.get(element);

    if (null == index) {
      throw new ElementNotFoundException("Elements not present in graph");
    }

    adjList.remove(adjList.get(index));
    currentIndex -= 1;

    for (HashSet<?> temp : adjList) {
      if (temp.contains(index)) {
        temp.remove(index);
      }
    }

  }

  @Override
  public boolean removeEdge(E element1, E element2) throws ElementNotFoundException {
    if (null == element1 || null == element2) {
      throw new NullNotAllowedException("Null elements not present in graph");
    }

    Integer index1 = nodeMap.get(element1);
    Integer index2 = nodeMap.get(element2);

    if (null == index1 || null == index2) {
      throw new ElementNotFoundException("Elements not present in graph");
    }

    if (!adjList.get(index1).contains(index2)) {
      return false;
    }

    adjList.get(index1).remove(index2);

    return true;
  }

}
