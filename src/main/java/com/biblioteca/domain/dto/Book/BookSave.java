package com.biblioteca.domain.dto.Book;

import java.util.List;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookSave(
    @NotBlank(message = "El titulo es obligatorio") String title,

    @Min(value = 1, message = "El libro debe tener al menos 1 página") @Max(value = 10000, message = "El número de páginas no puede exceder las 10,000") Integer numPages,

    @Min(value = 1000000000L, message = "El ISBN es demasiado corto") @Max(value = 9999999999999L, message = "El ISBN es demasiado largo") Long isbn,

    @NotNull(message = "Los iDs de los autores del libro son obligatorios") List<AuthorId> authors,

    @NotNull(message = "Los iDs de las categorias a las que pertenece el libro son obligatorios") List<CategoryId> categories) {

}
