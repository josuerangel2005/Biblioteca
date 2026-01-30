package com.biblioteca.persistence.crud;

import org.springframework.data.repository.ListPagingAndSortingRepository;

import com.biblioteca.persistence.entity.Libro;

public interface LibroSortingAndPageRepository extends ListPagingAndSortingRepository<Libro, Integer> {
}
