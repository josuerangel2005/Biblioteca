package com.biblioteca.domain.repository;

import java.util.List;

import com.biblioteca.domain.dto.Book.Book;
import com.biblioteca.domain.dto.Book.BookSave;
import com.biblioteca.domain.dto.Book.BookUpdate;

public interface BookRepository {

  List<Book> getAll();

  Book getBookById(int id);

  Book saveBook(BookSave bookSave);

  Book updateBook(BookUpdate bookUpdate, int id);

  void deleteBook(int id);
}
