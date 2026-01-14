package com.biblioteca.persistence.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.biblioteca.domain.dto.Loan.LoanBook;
import com.biblioteca.persistence.entity.PrestamoLibro;

@Mapper(componentModel = "spring")
public interface LoanBookMapper {
  @Mapping(target = "bookId", source = "prestamoLibroPK.idLibro")
  @Mapping(source = "cantidadLibros", target = "quantity")
  LoanBook toLoanBook(PrestamoLibro prestamoLibro);

  List<LoanBook> toLoandBooks(List<PrestamoLibro> prestamoLibros);

  @InheritInverseConfiguration
  @Mapping(target = "libro", ignore = true)
  @Mapping(target = "prestamo", ignore = true)
  PrestamoLibro toPrestamoLibro(LoanBook loanBook);

  List<PrestamoLibro> toPrestamoLibros(List<LoanBook> loanBooks);
}
