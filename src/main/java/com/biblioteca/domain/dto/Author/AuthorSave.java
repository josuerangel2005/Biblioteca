package com.biblioteca.domain.dto.Author;

import java.time.LocalDate;

public record AuthorSave(
    String name,
    String biography,
    String nationality,
    LocalDate birthdate) {
}
