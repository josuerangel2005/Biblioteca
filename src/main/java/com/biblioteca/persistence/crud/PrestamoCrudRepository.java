package com.biblioteca.persistence.crud;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.persistence.entity.Prestamo;

public interface PrestamoCrudRepository extends JpaRepository<Prestamo, Integer> {
  Prestamo findFirstByIdUsuarioAndFechaPrestamo(Integer idUsuario, LocalDateTime fechaPrestamo);

}
