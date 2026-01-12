package com.biblioteca.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.biblioteca.domain.dto.Book.BookSave;
import com.biblioteca.persistence.entity.Libro;

@Mapper(componentModel = "spring", uses = { BooksAuthorMapper.class, BookCategoryMapper.class })
public interface BookSaveMapper {

  @Mapping(source = "titulo", target = "title")
  @Mapping(source = "numPaginas", target = "numPages")
  @Mapping(source = "isbn", target = "isbn")
  @Mapping(source = "libroAutores", target = "authors")
  @Mapping(source = "libroCategorias", target = "categories")
  BookSave toBookSave(Libro libro);

  @InheritInverseConfiguration
  @Mapping(target = "prestamoLibros", ignore = true)
  @Mapping(target = "libroAutores", ignore = true)
  @Mapping(target = "libroCategorias", ignore = true)
  @Mapping(target = "disponible", ignore = true)
  @Mapping(target = "idLibro", ignore = true)
  Libro toLibro(BookSave bookSave);

}
