package com.biblioteca.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.biblioteca.domain.dto.Author.AuthorUpdate;
import com.biblioteca.persistence.entity.Autor;

@Mapper(componentModel = "spring", uses = { BooksAuthorMapper.class })
public interface AuthorUpdateMapper {

  @Mapping(target = "nombre", source = "name")
  @Mapping(target = "biografia", source = "biography")
  @Mapping(target = "nacionalidad", source = "nationality")
  @Mapping(target = "fechaNacimiento", source = "birthdate")
  @Mapping(target = "libroAutors", source = "books")
  void updateEntityFromDto(AuthorUpdate authorUpdate, @MappingTarget Autor autor);
}
