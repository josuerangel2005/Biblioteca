package com.biblioteca.web.exception;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.biblioteca.domain.exception.CategoryAlreadyExistsException;
import com.biblioteca.domain.exception.CategoryNotExistsException;

@RestControllerAdvice
public class CategoriaRestExceptionHandler {

  @ExceptionHandler(CategoryAlreadyExistsException.class)
  public ResponseEntity<Error> handleException(CategoryAlreadyExistsException categoryAlreadyExistsException) {
    return ResponseEntity.badRequest()
        .body(new Error("categoria-already-exists", categoryAlreadyExistsException.getMessage()));
  }

  @ExceptionHandler(CategoryNotExistsException.class)
  public ResponseEntity<Error> handleException(CategoryNotExistsException categoryNotExistsException) {
    return ResponseEntity.badRequest().body(new Error("category-not-exists", categoryNotExistsException.getMessage()));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<Error>> handleException(MethodArgumentNotValidException methodArgumentNotValidException) {
    return ResponseEntity.badRequest().body(
        methodArgumentNotValidException.getBindingResult().getFieldErrors().stream()
            .map(err -> new Error(err.getField(), err.getDefaultMessage())).toList());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Error> handleException(Exception exception) {
    return ResponseEntity.badRequest().body(new Error("unknow-error", exception.getMessage()));
  }
}
