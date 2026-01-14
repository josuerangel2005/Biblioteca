package com.biblioteca.domain.dto.Loan;

import java.time.LocalDateTime;
import java.util.List;

public record LoanUpdate(
    Integer userId,
    LocalDateTime loanDate,
    Boolean delivered,
    Integer bookQuiantity,
    List<LoanBook> books) {
}
