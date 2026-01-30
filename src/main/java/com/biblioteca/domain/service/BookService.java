package com.biblioteca.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.domain.dto.Book.Book;
import com.biblioteca.domain.dto.Book.BookSave;
import com.biblioteca.domain.dto.Book.BookUpdate;
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
}
