package com.biblioteca.persistence.crud;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.persistence.entity.Autor;

public interface AutorCrudRepository extends JpaRepository<Autor, Integer> {
  Autor findFirstByNombre(String nombre);

  List<Autor> findByFechaNacimientoBetween(LocalDate fechaInicio, LocalDate fechaFin);
}
