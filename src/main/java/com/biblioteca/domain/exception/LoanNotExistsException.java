package com.biblioteca.domain.exception;

public class LoanNotExistsException extends RuntimeException {
  public LoanNotExistsException(int id) {
    super("El prestamo con id " + id + " no existe");
  }
}
