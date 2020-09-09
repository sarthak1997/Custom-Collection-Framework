package com.epam.customcollection;

import com.epam.exceptions.DuplicateEntryException;
import com.epam.exceptions.ElementNotFoundException;

public interface Set<T> extends Iterable<T> {

  public void add(T object) throws DuplicateEntryException;

  public void remove(T object) throws ElementNotFoundException;

  public void update(T oldObject, T newObject) throws ElementNotFoundException,
      DuplicateEntryException;


}
