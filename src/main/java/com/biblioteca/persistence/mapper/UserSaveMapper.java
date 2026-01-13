package com.biblioteca.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.biblioteca.domain.dto.User.UserSave;
import com.biblioteca.persistence.entity.Usuario;

@Mapper(componentModel = "spring")
public interface UserSaveMapper {
  @Mapping(source = "nombre", target = "name")
  @Mapping(source = "cedula", target = "idCard")
  @Mapping(source = "fechaNacimiento", target = "birthdate")
  @Mapping(source = "direccion", target = "address")
  UserSave toUserSave(Usuario usuario);

  @InheritInverseConfiguration
  @Mapping(target = "idUsuario", ignore = true)
  @Mapping(target = "prestamos", ignore = true)
  Usuario toUsuario(UserSave userSave);
}
