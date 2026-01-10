package com.biblioteca.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.persistence.entity.Categoria;

public interface CategoriaCrudRepository extends JpaRepository<Categoria, Integer> {
  Categoria findFirstByNombre(String nombre);
}
