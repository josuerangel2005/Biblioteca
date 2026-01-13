package com.biblioteca.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.biblioteca.domain.dto.User.UserSave;
import com.biblioteca.persistence.entity.Usuario;

@Mapper(componentModel = "spring")
public interface UserUpdateMapper {
  @Mapping(source = "name", target = "nombre")
  @Mapping(source = "idCard", target = "cedula")
  @Mapping(source = "birthdate", target = "fechaNacimiento")
  @Mapping(source = "address", target = "direccion")
  @Mapping(target = "idUsuario", ignore = true)
  @Mapping(target = "prestamos", ignore = true)
  void updateUsuarioFromUserSave(UserSave userSave, @MappingTarget Usuario usuario);
}
