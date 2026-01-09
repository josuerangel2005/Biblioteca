package com.biblioteca.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.domain.dto.Author.AuthorResponse;
import com.biblioteca.domain.dto.Author.AuthorSave;
import com.biblioteca.domain.dto.Author.AuthorUpdate;
import com.biblioteca.domain.repository.AuthorRepository;

@Service
public class AuthorService {
  @Autowired
  private AuthorRepository authorRepository;

  public List<AuthorResponse> getAllsAuthor() {
    return this.authorRepository.getAllsAuthor();
  }

  public AuthorResponse getAuthorById(int id) {
    return this.authorRepository.getAuthorById(id);
  }

  public AuthorResponse saveAuthor(AuthorSave authorSave) {
    return this.authorRepository.saveAuthor(authorSave);
  }

  public AuthorResponse updateAuthor(int id, AuthorUpdate authorUpdate) {
    return this.authorRepository.updateAuthor(id, authorUpdate);
  }

  public void deleteAuthor(int id) {
    this.authorRepository.deleteAuthor(id);
  }
}
