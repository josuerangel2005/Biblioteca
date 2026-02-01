package com.biblioteca.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.domain.dto.UpdatePageBookDto;
import com.biblioteca.domain.dto.Book.Book;
import com.biblioteca.domain.dto.Book.BookSave;
import com.biblioteca.domain.dto.Book.BookUpdate;
import com.biblioteca.domain.service.BookService;
import com.biblioteca.persistence.projection.LibroVeces;

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

  @GetMapping("/available")
  public ResponseEntity<List<Book>> findDisponibleTrue() {
    return ResponseEntity.ok(this.bookService.findByDisponibleTrue());
  }

  @GetMapping("/books/{pages}")
  public ResponseEntity<List<Book>> findByNumPaginasGreaterThan(@PathVariable int pageNums) {
    return ResponseEntity.ok(this.bookService.findByNumPaginasGreaterThan(pageNums));
  }

  @GetMapping("book/{title}")
  public ResponseEntity<Book> findFirstByTitulo(@PathVariable String title) {
    return ResponseEntity.ok(this.bookService.findFirstByTitulo(title));
  }

  @GetMapping("/mloan")
  public ResponseEntity<List<LibroVeces>> getMostLoanBooks() {
    return ResponseEntity.ok(this.bookService.getMostLoanBooks());
  }

  @GetMapping("/libroa")
  public ResponseEntity<List<Book>> librosAutores() {
    return ResponseEntity.ok(this.bookService.librosAutores());
  }

  @GetMapping("/book/{category}")
  public ResponseEntity<List<Book>> libroCategoria(@PathVariable String categoryName) {
    return ResponseEntity.ok(this.bookService.librosByCategoria(categoryName));
  }

  @GetMapping("/noloan")
  public ResponseEntity<List<Book>> noLoanBooks() {
    return ResponseEntity.ok(this.bookService.noLoanBooks());
  }

  @GetMapping("/page")
  public ResponseEntity<Page<Book>> pageAndSortingBooks(@RequestParam int pages, @RequestParam int elements,
      @RequestParam String sortBy, @RequestParam String direction) {
    return ResponseEntity.ok(this.bookService.pageAndSortingBooks(pages, elements, sortBy, direction));
  }

  @PutMapping("/uppages")
  public ResponseEntity<Void> updatePagesOfBook(@RequestBody UpdatePageBookDto updatePageBookDto) {
    this.bookService.updatePagesOfBook(updatePageBookDto);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/state/{idLibro}")
  public ResponseEntity<Boolean> updateStateOfBook(@PathVariable int idLibro) {
    return ResponseEntity.ok(this.bookService.updateStateOfBook(idLibro));
  }
}
