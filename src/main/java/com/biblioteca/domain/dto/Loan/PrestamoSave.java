package com.biblioteca.domain.dto.Loan;

import java.time.LocalDate;
import java.util.List;

public record PrestamoSave(
    Integer userId,
    LocalDate loanDate,
    Integer bookQuiantity,
    List<LoanBook> books) {
}
