package com.biblioteca.persistence.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import com.biblioteca.domain.dto.Book.Book;
import com.biblioteca.persistence.entity.Libro;

import jakarta.validation.groups.Default;

@Mapper(componentModel = "spring", uses = { AuthorBookMapper.class, BookCategoryMapper.class, AvailableMapper.class })
public interface BookMapper {
  @Mapping(source = "idLibro", target = "bookId")
  @Mapping(source = "titulo", target = "title")
  @Mapping(source = "numPaginas", target = "numPages")
  @Mapping(source = "isbn", target = "isbn")
  @Mapping(source = "disponible", target = "available", qualifiedByName = "BooleanToString")
  @Mapping(source = "libroAutores", target = "authors")
  @Mapping(source = "libroCategorias", target = "categories")
  Book toBook(Libro libro);

  List<Book> toBooks(List<Libro> libros);

  @InheritInverseConfiguration
  @Mapping(source = "available", target = "disponible", qualifiedByName = "StringToBoolean")
  @Mapping(target = "prestamoLibros", ignore = true)
  Libro toLibro(Book book);
}
