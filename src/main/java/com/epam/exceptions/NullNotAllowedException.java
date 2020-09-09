package com.epam.exceptions;

public class NullNotAllowedException extends RuntimeException {

  private static final long serialVersionUID = 1L;
  
  public NullNotAllowedException(String message) {
    super(message);
  }

}
