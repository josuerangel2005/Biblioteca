package com.biblioteca.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.biblioteca.domain.dto.Author.AuthorSave;
import com.biblioteca.persistence.entity.Autor;

@Mapper(componentModel = "spring")
public interface AuthorSaveMapper {
  @Mapping(target = "idAutor", ignore = true)
  @Mapping(target = "libroAutors", ignore = true)
  @Mapping(source = "name", target = "nombre")
  @Mapping(source = "biography", target = "biografia")
  @Mapping(source = "nationality", target = "nacionalidad")
  @Mapping(source = "birthdate", target = "fechaNacimiento")
  Autor toAutor(AuthorSave authorSave);

  @InheritInverseConfiguration
  AuthorSave toAuthor(Autor autor);

}
