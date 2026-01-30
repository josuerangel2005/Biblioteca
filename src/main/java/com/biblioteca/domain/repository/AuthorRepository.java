package com.biblioteca.domain.repository;

import java.time.LocalDate;
import java.util.List;

import com.biblioteca.domain.dto.Author.AuthorResponse;
import com.biblioteca.domain.dto.Author.AuthorSave;
import com.biblioteca.domain.dto.Author.AuthorUpdate;
import com.biblioteca.persistence.entity.Autor;

public interface AuthorRepository {
  List<AuthorResponse> getAllAuthor();

  AuthorResponse findFirstByNombre(String nombre);

  AuthorResponse getAuthorById(int id);

  AuthorResponse saveAuthor(AuthorSave author);

  AuthorResponse updateAuthor(int id, AuthorUpdate author);

  void deleteAuthor(int id);

  List<Autor> findByFechaNacimientoBetween(LocalDate fechaInicio, LocalDate fechaFin);
}
