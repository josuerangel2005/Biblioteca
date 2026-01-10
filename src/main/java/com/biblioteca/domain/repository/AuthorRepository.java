package com.biblioteca.domain.repository;

import java.util.List;

import com.biblioteca.domain.dto.Author.AuthorResponse;
import com.biblioteca.domain.dto.Author.AuthorSave;
import com.biblioteca.domain.dto.Author.AuthorUpdate;

public interface AuthorRepository {
  List<AuthorResponse> getAllAuthor();

  AuthorResponse getAuthorById(int id);

  AuthorResponse saveAuthor(AuthorSave author);

  AuthorResponse updateAuthor(int id, AuthorUpdate author);

  void deleteAuthor(int id);
}
