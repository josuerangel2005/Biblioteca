package com.biblioteca.domain.dto.User;

import java.time.LocalDate;
import java.util.List;

public record User(
    Integer userId,
    String name,
    String idCard,
    LocalDate birthdate,
    String address,
    List<LoanId> loansId) {
}
