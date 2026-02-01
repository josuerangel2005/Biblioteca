package com.biblioteca.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.domain.dto.UpdatePageBookDto;
import com.biblioteca.domain.dto.Book.Book;
import com.biblioteca.domain.dto.Book.BookSave;
import com.biblioteca.domain.dto.Book.BookUpdate;
import com.biblioteca.persistence.projection.LibroVeces;
import com.biblioteca.persistence.repository.BookRepositoryJPA;

@Service
public class BookService {
  @Autowired
  private BookRepositoryJPA bookRepositoryJPA;

  @Transactional(readOnly = true)
  public List<Book> getAll() {
    return this.bookRepositoryJPA.getAll();
  }

  @Transactional(readOnly = true)
  public Book getBookById(int id) {
    return this.bookRepositoryJPA.getBookById(id);
  }

  @Transactional(rollbackFor = Exception.class)
  public Book saveBook(BookSave bookSave) {
    return this.bookRepositoryJPA.saveBook(bookSave);
  }

  @Transactional(rollbackFor = Exception.class)
  public Book updateBook(BookUpdate bookUpdate, int id) {
    return this.bookRepositoryJPA.updateBook(bookUpdate, id);
  }

  @Transactional(rollbackFor = Exception.class)
  public void deleteBook(int id) {
    this.bookRepositoryJPA.deleteBook(id);
  }

  @Transactional(readOnly = true)
  public List<Book> findByDisponibleTrue() {
    return this.bookRepositoryJPA.findByDisponibleTrue();
  }

  @Transactional(readOnly = true)
  public List<Book> findByNumPaginasGreaterThan(int numPaginas) {
    return this.bookRepositoryJPA.findByNumPaginasGreaterThan(numPaginas);
  }

  @Transactional(readOnly = true)
  public Book findFirstByTitulo(String titulo) {
    return this.bookRepositoryJPA.findFirstByTitulo(titulo);
  }

  @Transactional(readOnly = true)
  public List<LibroVeces> getMostLoanBooks() {
    return this.bookRepositoryJPA.getMostLoanBooks();
  }

  @Transactional(readOnly = true)
  public List<Book> librosAutores() {
    return this.bookRepositoryJPA.librosAutores();
  }

  @Transactional(readOnly = true)
  public List<Book> librosByCategoria(String categoryName) {
    return this.bookRepositoryJPA.librosByCategoria(categoryName);
  }

  @Transactional(readOnly = true)
  public List<Book> noLoanBooks() {
    return this.bookRepositoryJPA.noLoanBooks();
  }

  @Transactional(readOnly = true)
  public Page<Book> pageAndSortingBooks(int pages, int elements, String sortBy, String direction) {
    return this.bookRepositoryJPA.pageAndSortingBooks(pages, elements, sortBy, direction);
  }

  @Transactional(readOnly = true)
  public void updatePagesOfBook(UpdatePageBookDto updatePageBookDto) {
    this.bookRepositoryJPA.updatePagesOfBook(updatePageBookDto);
  }

  @Transactional(readOnly = true)
  public boolean updateStateOfBook(int idLibro) {
    return this.bookRepositoryJPA.updateStateOfBook(idLibro);
  }
}
