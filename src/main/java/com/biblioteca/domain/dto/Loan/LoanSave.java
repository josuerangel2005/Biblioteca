package com.biblioteca.domain.dto.Loan;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record LoanSave(
    Integer userId,
    LocalDateTime loanDate,
    Integer bookQuiantity,
    List<LoanBook> books) {
}
