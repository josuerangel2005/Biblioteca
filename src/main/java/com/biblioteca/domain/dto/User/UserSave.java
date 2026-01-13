package com.biblioteca.domain.dto.User;

import java.time.LocalDate;

public record UserSave(
    String name,
    String idCard,
    LocalDate birthdate,
    String address) {
}
