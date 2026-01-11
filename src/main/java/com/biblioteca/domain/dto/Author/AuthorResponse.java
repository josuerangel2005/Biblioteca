package com.biblioteca.domain.dto.Author;

import java.time.LocalDate;
import java.util.List;

import com.biblioteca.domain.dto.BooksAuthor;

public record AuthorResponse(
    Integer authorId,
    String name,
    String biography,
    String nationality,
    LocalDate birthdate,
    List<BooksAuthor> books) {
}
