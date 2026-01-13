package com.biblioteca.domain.dto.Loan;

import java.time.LocalDate;
import java.util.List;

public record PrestamoUpdate(
    Integer userId,
    LocalDate loanDate,
    Boolean delivered,
    Integer bookQuiantity,
    List<LoanBook> books) {
}
