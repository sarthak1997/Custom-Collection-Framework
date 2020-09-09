package com.epam.customcollection;

/**
 * Functional Interface for lambda expression of find method in custom set.
 * @author Sarthak_Jain
 *
 * @param <E> for the genericness of collection.
 */
public interface FindResolver<E> {

  public boolean resolve(E listObj);

}
