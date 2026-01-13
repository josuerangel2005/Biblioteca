package com.biblioteca.domain.dto.Loan;

import java.time.LocalDate;
import java.util.List;

public record Prestamo(
    Integer loadId,
    Integer userId,
    LocalDate loanDate,
    Integer bookQuiantity,
    Boolean delivered,
    List<LoanBook> books) {
}
