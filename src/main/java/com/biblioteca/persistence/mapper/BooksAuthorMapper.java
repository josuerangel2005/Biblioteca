package com.biblioteca.persistence.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.biblioteca.domain.dto.BooksAuthor;
import com.biblioteca.domain.dto.Book.AuthorId;
import com.biblioteca.persistence.entity.LibroAutor;

@Mapper(componentModel = "spring")
public interface BooksAuthorMapper {
  @Mapping(source = "libro.titulo", target = "bookName")
  BooksAuthor toBooksAuthor(LibroAutor libroAutor);

  @Mapping(source = "idAuthor", target = "libroAutorPK.idAutor")
  @Mapping(target = "libro", ignore = true)
  @Mapping(target = "autor", ignore = true)
  LibroAutor toLibroAutor(AuthorId id);

  List<LibroAutor> toLibroAutors(List<AuthorId> id);

  @InheritInverseConfiguration
  @Mapping(source = "libroAutorPK.idAutor", target = "idAuthor")
  AuthorId toInteger(LibroAutor libroAutor);

  List<AuthorId> tAuthorIds(List<LibroAutor> libroAutors);
}
