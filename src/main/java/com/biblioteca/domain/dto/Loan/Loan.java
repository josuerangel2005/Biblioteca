package com.biblioteca.domain.dto.Loan;

import java.time.LocalDateTime;
import java.util.List;

public record Loan(
    Integer loadId,
    Integer userId,
    LocalDateTime loanDate,
    Integer bookQuantity,
    Boolean delivered,
    List<LoanBook> books) {
}
