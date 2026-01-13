package com.biblioteca.domain.exception;

public class UserNotExistsException extends RuntimeException {
  public UserNotExistsException(int id) {
    super("El usuario con id " + id + " no existe");
  }
}
