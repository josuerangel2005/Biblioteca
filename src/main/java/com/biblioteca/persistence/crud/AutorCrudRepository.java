package com.biblioteca.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.persistence.entity.Autor;

public interface AutorCrudRepository extends JpaRepository<Autor, Integer> {
}
