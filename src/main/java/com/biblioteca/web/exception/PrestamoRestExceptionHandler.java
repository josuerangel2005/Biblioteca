package com.biblioteca.web.exception;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.biblioteca.domain.exception.LoanAlreadyExistsException;
import com.biblioteca.domain.exception.LoanNotExistsException;

@RestControllerAdvice
public class PrestamoRestExceptionHandler {

  @ExceptionHandler(LoanNotExistsException.class)
  public ResponseEntity<Error> handleException(LoanNotExistsException loanNotExistsException) {
    return ResponseEntity.badRequest().body(new Error("loan-not-exists", loanNotExistsException.getMessage()));
  }

  @ExceptionHandler(LoanAlreadyExistsException.class)
  public ResponseEntity<Error> handleException(LoanAlreadyExistsException loanAlreadyExistsException) {
    return ResponseEntity.badRequest().body(new Error("loan-already-exists", loanAlreadyExistsException.getMessage()));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<Error>> handleException(MethodArgumentNotValidException methodArgumentNotValidException) {
    return ResponseEntity.badRequest().body(methodArgumentNotValidException.getBindingResult().getFieldErrors().stream()
        .map(err -> new Error(err.getField(), err.getDefaultMessage())).toList());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Error> handleException(Exception exception) {
    return ResponseEntity.badRequest().body(new Error("unknow-error", exception.getMessage()));
  }
}
