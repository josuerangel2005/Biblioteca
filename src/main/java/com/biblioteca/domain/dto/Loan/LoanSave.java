package com.biblioteca.domain.dto.Loan;

import java.util.List;

public record LoanSave(
    Integer userId,
    Integer bookQuantity,
    List<LoanBook> books) {
}
