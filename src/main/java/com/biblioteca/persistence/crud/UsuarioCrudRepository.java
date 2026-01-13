package com.biblioteca.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.persistence.entity.Usuario;

public interface UsuarioCrudRepository extends JpaRepository<Usuario, Integer> {
  Usuario findFirstByNombre(String nombre);
}
