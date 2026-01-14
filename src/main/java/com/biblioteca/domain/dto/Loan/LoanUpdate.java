package com.biblioteca.domain.dto.Loan;

import java.util.List;

public record LoanUpdate(
    Integer userId,
    Boolean delivered,
    Integer bookQuantity,
    List<LoanBook> books) {
}
