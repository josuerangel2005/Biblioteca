package com.biblioteca.domain.exception;

public class BookAlreadyExistsException extends RuntimeException {
  public BookAlreadyExistsException(String title) {
    super("El libro con nombre " + title + " no existe");
  }
}
