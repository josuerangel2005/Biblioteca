package com.biblioteca.domain.dto.Loan;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public record LoanUpdate(
    @NotNull(message = "Id de usuario es obligatorio") Integer userId,
    @NotNull(message = "Estado de prestamo obligatorio") Boolean delivered,
    @NotNull(message = "Cantidad total de libros prestados es obligatoria") Integer bookQuantity,
    @NotNull(message = "ids de libros es obligatoria") List<LoanBook> books) {
}
