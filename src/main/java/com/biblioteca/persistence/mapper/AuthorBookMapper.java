package com.biblioteca.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.biblioteca.domain.dto.AuthorBook;
import com.biblioteca.persistence.entity.LibroAutor;

@Mapper(componentModel = "spring")
public interface AuthorBookMapper {
  @Mapping(source = "autor.nombre", target = "name")
  AuthorBook toAuthorBook(LibroAutor libroAutor);
}
