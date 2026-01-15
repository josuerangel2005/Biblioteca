package com.biblioteca.web.exception;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.biblioteca.domain.exception.*;

@RestControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler({
      AuthorNotExistsException.class,
      CategoryNotExistsException.class,
      BookNotExistsException.class,
      LoanNotExistsException.class,
      UserNotExistsException.class
  })
  public ResponseEntity<Error> handleNotExists(RuntimeException ex) {
    return ResponseEntity.badRequest()
        .body(new Error("not-exists", ex.getMessage()));
  }

  @ExceptionHandler({
      AuthorAlreadyExistsException.class,
      CategoryAlreadyExistsException.class,
      BookAlreadyExistsException.class,
      LoanAlreadyExistsException.class,
      UserAlreadyExistsException.class
  })
  public ResponseEntity<Error> handleAlreadyExists(RuntimeException ex) {
    return ResponseEntity.badRequest()
        .body(new Error("already-exists", ex.getMessage()));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<Error>> handleValidation(MethodArgumentNotValidException ex) {
    return ResponseEntity.badRequest().body(
        ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(err -> new Error(err.getField(), err.getDefaultMessage()))
            .toList());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Error> handleGeneric(Exception ex) {
    return ResponseEntity.badRequest()
        .body(new Error("unknown-error", ex.getMessage()));
  }
}
