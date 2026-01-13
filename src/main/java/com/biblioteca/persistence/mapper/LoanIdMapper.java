package com.biblioteca.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.biblioteca.domain.dto.User.LoanId;
import com.biblioteca.persistence.entity.Prestamo;

@Mapper(componentModel = "spring")
public interface LoanIdMapper {
  @Mapping(source = "idPrestamo", target = "loanId")
  LoanId toLoandId(Prestamo prestamo);

  List<LoanId> toLoanIds(List<Prestamo> prestamos);
}
