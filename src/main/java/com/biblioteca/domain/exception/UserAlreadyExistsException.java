package com.biblioteca.domain.exception;

public class UserAlreadyExistsException extends RuntimeException {
  public UserAlreadyExistsException(String nombre) {
    super("El usuario con nombre " + nombre + " no existe");
  }
}
