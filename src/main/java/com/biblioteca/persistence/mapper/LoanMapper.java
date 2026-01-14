package com.biblioteca.persistence.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.biblioteca.domain.dto.Loan.Loan;
import com.biblioteca.persistence.entity.Prestamo;

@Mapper(componentModel = "spring", uses = { LoanBookMapper.class })
public interface LoanMapper {
  @Mapping(target = "loadId", source = "idPrestamo")
  @Mapping(target = "userId", source = "idUsuario")
  @Mapping(target = "loanDate", source = "fechaPrestamo")
  @Mapping(target = "bookQuantity", source = "cantidadLibros")
  @Mapping(target = "delivered", source = "entregado")
  @Mapping(target = "books", source = "prestamoLibros")
  Loan toLoan(Prestamo prestamo);

  List<Loan> toLoans(List<Prestamo> prestamos);

  @InheritInverseConfiguration
  @Mapping(target = "usuario", ignore = true)
  Prestamo toPrestamo(Loan loan);

  List<Prestamo> toPrestamos(List<Loan> loans);
}
