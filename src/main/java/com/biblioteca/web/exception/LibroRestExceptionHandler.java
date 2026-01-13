package com.biblioteca.web.exception;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.biblioteca.domain.exception.BookAlreadyExistsException;
import com.biblioteca.domain.exception.BookNotExistsException;

@RestControllerAdvice
public class LibroRestExceptionHandler {

  @ExceptionHandler(BookNotExistsException.class)
  public ResponseEntity<Error> handleException(BookNotExistsException bookNotExistsException) {
    return ResponseEntity.badRequest().body(new Error("book-not-exists", bookNotExistsException.getMessage()));
  }

  @ExceptionHandler(BookAlreadyExistsException.class)
  public ResponseEntity<Error> handleException(BookAlreadyExistsException bookAlreadyExistsException) {
    return ResponseEntity.badRequest().body(new Error("book-already-exists", bookAlreadyExistsException.getMessage()));
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
