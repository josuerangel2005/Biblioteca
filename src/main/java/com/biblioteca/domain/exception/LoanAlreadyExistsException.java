package com.biblioteca.domain.exception;

public class LoanAlreadyExistsException extends RuntimeException {
  public LoanAlreadyExistsException(int idUsuario, String fecha) {
    super("El prestamo con id usuario " + idUsuario + " y fecha " + fecha + " ya existe");
  }
}
