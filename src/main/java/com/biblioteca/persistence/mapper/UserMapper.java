package com.biblioteca.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.biblioteca.domain.dto.User.User;
import com.biblioteca.persistence.entity.Usuario;

@Mapper(componentModel = "spring", uses = { LoanIdMapper.class })
public interface UserMapper {
  @Mapping(source = "idUsuario", target = "userId")
  @Mapping(source = "nombre", target = "name")
  @Mapping(source = "cedula", target = "idCard")
  @Mapping(source = "fechaNacimiento", target = "birthdate")
  @Mapping(source = "direccion", target = "address")
  @Mapping(source = "prestamos", target = "loansId")
  User toUser(Usuario usuario);

  List<User> toUsers(List<Usuario> usuarios);
}
