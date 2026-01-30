package com.biblioteca.domain.repository;

import java.util.List;

import org.springframework.data.domain.Page;

import com.biblioteca.domain.dto.UpdatePageBookDto;
import com.biblioteca.domain.dto.Book.Book;
import com.biblioteca.domain.dto.Book.BookSave;
import com.biblioteca.domain.dto.Book.BookUpdate;
import com.biblioteca.persistence.entity.Libro;
import com.biblioteca.persistence.projection.LibroVeces;

public interface BookRepository {

  List<Book> getAll();

  Book findFirstByTitulo(String titulo);

  List<Book> findByDisponibleTrue();

  List<Book> findByNumPaginasGreaterThan(int numPaginas);

  List<Book> librosAutores();

  List<Book> librosByCategoria(String categoryName);

  List<LibroVeces> getMostLoanBooks();

  List<Book> noLoanBooks();

  void updatePagesOfBook(UpdatePageBookDto updatePageBookDto);

  boolean updateStateOfBook(int idLibro);

  Book getBookById(int id);

  Book saveBook(BookSave bookSave);

  Book updateBook(BookUpdate bookUpdate, int id);

  void deleteBook(int id);

  Page<Book> pageAndSortingBooks(int pages, int elements, String sortBy, String direction);
}
