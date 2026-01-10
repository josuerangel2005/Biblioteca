package com.biblioteca.web.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.biblioteca.domain.exception.AuthorAlreadyExistsException;
import com.biblioteca.domain.exception.AuthorNotExistsException;

@RestControllerAdvice
public class AutorRestExceptionHandler {

  @ExceptionHandler(AuthorAlreadyExistsException.class)
  public ResponseEntity<Error> hadleException(AuthorAlreadyExistsException authorAlreadyExistsException) {
    return ResponseEntity.badRequest()
        .body(new Error("author-already-exists", authorAlreadyExistsException.getMessage()));
  }

  @ExceptionHandler(AuthorNotExistsException.class)
  public ResponseEntity<Error> handleException(AuthorNotExistsException authorNotExistsException) {
    return ResponseEntity.badRequest().body(new Error("author-not-exists", authorNotExistsException.getMessage()));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<Error>> handleException(MethodArgumentNotValidException methodArgumentNotValidException) {
    List<Error> errors = new ArrayList<>();

    methodArgumentNotValidException.getBindingResult().getFieldErrors()
        .forEach(err -> errors.add(new Error(err.getField(), err.getDefaultMessage())));

    return ResponseEntity.badRequest().body(errors);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Error> handleException(Exception exception) {
    return ResponseEntity.badRequest().body(new Error("unknown-error", exception.getMessage()));
  }
}
