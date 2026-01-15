package com.biblioteca.domain.dto.Loan;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public record LoanSave(
    @NotNull(message = "Id de usuario es obliagtorio") Integer userId,
    @NotNull(message = "Cantidad de libros es obligatoria") Integer bookQuantity,
    @NotNull(message = "Ids de libros son obliagtorios") List<LoanBook> books) {
}
