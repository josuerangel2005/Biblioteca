package com.biblioteca.domain.exception;

public class CategoryNotExistsException extends RuntimeException {
  public CategoryNotExistsException(int id) {
    super("La categoria con id " + id + " no existe");
  }
}
