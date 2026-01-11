package com.biblioteca.domain.dto.Book;

import java.util.List;

public record BookUpdate(
    String title,
    Integer numPages,
    Long isbn,
    String available,
    List<AuthorId> authors,
    List<CategoryId> categories) {
}
