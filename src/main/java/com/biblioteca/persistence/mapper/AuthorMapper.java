package com.biblioteca.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.biblioteca.domain.dto.Author.AuthorResponse;
import com.biblioteca.persistence.entity.Autor;

@Mapper(componentModel = "spring", uses = { BooksAuthorMapper.class })
public interface AuthorMapper {

  @Mapping(source = "idAutor", target = "idAuthor")
  @Mapping(source = "nombre", target = "name")
  @Mapping(source = "biografia", target = "biography")
  @Mapping(source = "nacionalidad", target = "nationality")
  @Mapping(source = "fechaNacimiento", target = "birthdate")
  @Mapping(source = "libroAutors", target = "books")
  AuthorResponse toAuthor(Autor autor);

  List<AuthorResponse> toAuthors(List<Autor> autors);

}
