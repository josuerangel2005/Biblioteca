package com.biblioteca.domain.exception;

public class BookNotExistsException extends RuntimeException {
  public BookNotExistsException(int id) {
    super("El libro con id " + id + " no existe");
  }
}
