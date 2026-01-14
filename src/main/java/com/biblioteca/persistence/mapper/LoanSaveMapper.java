package com.biblioteca.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.biblioteca.domain.dto.Loan.LoanSave;
import com.biblioteca.persistence.entity.Prestamo;

@Mapper(componentModel = "spring", uses = { LoanBookMapper.class })
public interface LoanSaveMapper {

  @Mapping(source = "idUsuario", target = "userId")
  @Mapping(source = "cantidadLibros", target = "bookQuantity")
  @Mapping(source = "prestamoLibros", target = "books")
  LoanSave toLoanSave(Prestamo prestamo);

  @InheritInverseConfiguration
  @Mapping(target = "idPrestamo", ignore = true)
  @Mapping(target = "entregado", ignore = true)
  @Mapping(target = "usuario", ignore = true)
  @Mapping(target = "fechaPrestamo", ignore = true)
  Prestamo toPrestamo(LoanSave loanSave);
}
