package com.biblioteca.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.biblioteca.domain.dto.BooksAuthor;
import com.biblioteca.persistence.entity.LibroAutor;

@Mapper(componentModel = "spring")
public interface BooksAuthorMapper {
  @Mapping(source = "libro.titulo", target = "bookName")
  BooksAuthor toBooksAuthor(LibroAutor libroAutor);
}
