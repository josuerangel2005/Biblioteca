package com.biblioteca.domain.exception;

public class AuthorNotExistsException extends RuntimeException {
  public AuthorNotExistsException(int id) {
    super("El autor con id " + id + " no existe");
  }
}
