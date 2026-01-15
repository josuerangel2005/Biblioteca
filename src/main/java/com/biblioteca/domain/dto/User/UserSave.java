package com.biblioteca.domain.dto.User;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public record UserSave(
    @NotBlank(message = "Nombre es obligatorio") @Min(value = 3, message = "Nombre debe tener 3 caracteres como mínimo") String name,
    @NotBlank(message = "Cédula obligatoria") String idCard,
    @NotNull(message = "Fecha de nacimiento obligatoria") @PastOrPresent(message = "La fecha debe ser igual o anterior a la actual") LocalDate birthdate,
    @NotBlank(message = "Dirección obligatoria") String address) {
}
