package com.biblioteca.domain.exception;

public class AuthorAlreadyExistsException extends RuntimeException {
  public AuthorAlreadyExistsException(String authorName) {
    super("El autor llamdo " + authorName + " ya existe en base de datos");
  }
}
