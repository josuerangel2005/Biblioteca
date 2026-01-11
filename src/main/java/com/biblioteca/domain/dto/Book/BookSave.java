package com.biblioteca.domain.dto.Book;

import java.util.List;

public record BookSave(
    String title,
    Integer numPages,
    Long isbn,
    List<AuthorId> authors,
    List<CategoryId> categories) {
}
