package com.biblioteca.domain.dto.Book;

import java.util.List;

import com.biblioteca.domain.dto.AuthorBook;
import com.biblioteca.domain.dto.BookCategory;

public record Book(
    Integer bookId,
    String title,
    Integer numPages,
    Long isbn,
    String available,
    List<AuthorBook> authors,
    List<BookCategory> categories) {
}
