package com.biblioteca.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.biblioteca.domain.dto.Book.BookUpdate;
import com.biblioteca.persistence.entity.Libro;

@Mapper(componentModel = "spring", uses = { AvailableMapper.class, BooksAuthorMapper.class, BookCategoryMapper.class })
public interface BookUpdateMapper {
  @Mapping(target = "idLibro", ignore = true)
  @Mapping(target = "titulo", source = "title")
  @Mapping(target = "numPaginas", source = "numPages")
  @Mapping(target = "isbn", source = "isbn")
  @Mapping(target = "disponible", source = "available", qualifiedByName = "StringToBoolean")
  @Mapping(target = "prestamoLibros", ignore = true)
  @Mapping(target = "libroAutores", ignore = true)
  @Mapping(target = "libroCategorias", ignore = true)
  void updateLibroFromBookSave(BookUpdate bookUpdate, @MappingTarget Libro libro);
}
