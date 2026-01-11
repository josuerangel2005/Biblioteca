package com.biblioteca.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.domain.dto.Book.Book;
import com.biblioteca.domain.dto.Book.BookSave;
import com.biblioteca.domain.dto.Book.BookUpdate;
import com.biblioteca.domain.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
  @Autowired
  private BookService bookService;

  @GetMapping
  public ResponseEntity<List<Book>> getAll() {
    return new ResponseEntity<List<Book>>(this.bookService.getAll(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Book> getBookById(@PathVariable int id) {
    return new ResponseEntity<Book>(this.bookService.getBookById(id), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Book> saveBook(@RequestBody BookSave bookSave) {
    return new ResponseEntity<Book>(this.bookService.saveBook(bookSave), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Book> updateBook(@RequestBody BookUpdate bookUpdate, @PathVariable int id) {
    return new ResponseEntity<Book>(this.bookService.updateBook(bookUpdate, id), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBook(@PathVariable int id) {
    this.bookService.deleteBook(id);
    return ResponseEntity.ok().build();
  }
}
