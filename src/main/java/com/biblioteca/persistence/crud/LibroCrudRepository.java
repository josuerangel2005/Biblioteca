package com.biblioteca.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import com.biblioteca.persistence.entity.Libro;

public interface LibroCrudRepository extends JpaRepository<Libro, Integer> {
}
