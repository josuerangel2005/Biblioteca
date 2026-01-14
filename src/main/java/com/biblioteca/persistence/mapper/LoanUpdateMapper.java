package com.biblioteca.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.biblioteca.domain.dto.Loan.LoanUpdate;
import com.biblioteca.persistence.entity.Prestamo;

@Mapper(componentModel = "spring", uses = { LoanBookMapper.class })
public interface LoanUpdateMapper {
  @Mapping(source = "idUsuario", target = "userId")
  @Mapping(source = "fechaPrestamo", target = "loanDate")
  @Mapping(source = "entregado", target = "delivered")
  @Mapping(source = "cantidadLibros", target = "bookQuiantity")
  @Mapping(source = "prestamoLibros", target = "books")
  LoanUpdate toLoanUpdate(Prestamo prestamo);

  @InheritInverseConfiguration
  @Mapping(target = "idPrestamo", ignore = true)
  @Mapping(target = "usuario", ignore = true)
  Prestamo toPrestamo(LoanUpdate loanUpdate);

  @Mapping(source = "userId", target = "idUsuario")
  @Mapping(source = "loanDate", target = "fechaPrestamo")
  @Mapping(source = "delivered", target = "entregado")
  @Mapping(source = "bookQuiantity", target = "cantidadLibros")
  @Mapping(source = "books", target = "prestamoLibros")
  @Mapping(target = "idPrestamo", ignore = true)
  @Mapping(target = "usuario", ignore = true)
  void updatePrestamoFromLoan(LoanUpdate loanUpdate, @MappingTarget Prestamo prestamo);
}
