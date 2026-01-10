package com.biblioteca.domain.dto.Category;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;

public record CategorySave(
    @NotBlank(message = "El nombre es obligatorio") @Length(min = 5) String name) {
}
