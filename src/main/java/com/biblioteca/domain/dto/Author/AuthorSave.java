package com.biblioteca.domain.dto.Author;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

public record AuthorSave(

    @NotBlank(message = "El nombre del autor es obligatorio") @Length(min = 3, message = "Debe tener como mínimo 3 caracteres") String name,

    @NotBlank(message = "La biografia del autor es obligatoria") @Length(min = 50, message = "Debe tener como mínimo 50 caracteres") String biography,

    @NotBlank(message = "La nacionalidad del autor es obligatoria") @Length(min = 5, message = "Debe tener como mínimo 5 caracteres") String nationality,

    @PastOrPresent(message = "La fecha de nacimiento debe ser anterior o igual a la actual") LocalDate birthdate) {
}
