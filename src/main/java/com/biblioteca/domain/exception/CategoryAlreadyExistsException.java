package com.biblioteca.domain.exception;

public class CategoryAlreadyExistsException extends RuntimeException {
  public CategoryAlreadyExistsException(String name) {
    super("La categoria con nombre " + name + " ya existe");
  }
}
