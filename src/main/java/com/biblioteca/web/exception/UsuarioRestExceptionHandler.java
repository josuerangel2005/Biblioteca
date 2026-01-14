package com.biblioteca.web.exception;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.biblioteca.domain.exception.UserAlreadyExistsException;
import com.biblioteca.domain.exception.UserNotExistsException;

@RestControllerAdvice
public class UsuarioRestExceptionHandler {

  @ExceptionHandler(UserNotExistsException.class)
  public ResponseEntity<Error> handleException(UserNotExistsException userNotExistsException) {
    return ResponseEntity.badRequest().body(new Error("user-not-exists", userNotExistsException.getMessage()));
  }

  @ExceptionHandler(UserAlreadyExistsException.class)
  public ResponseEntity<Error> handleException(UserAlreadyExistsException userAlreadyExistsException) {
    return ResponseEntity.badRequest().body(new Error("user-already-exists", userAlreadyExistsException.getMessage()));
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
