package com.biblioteca.domain.dto.Loan;

import java.time.LocalDate;
import java.util.List;

public record Loan(
    Integer loadId,
    Integer userId,
    LocalDate loanDate,
    Integer bookQuantity,
    Boolean delivered,
    List<LoanBook> books) {
}
