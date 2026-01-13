package com.biblioteca.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.persistence.entity.Prestamo;

public interface PrestamoCrudRepository extends JpaRepository<Prestamo, Integer> {

}
