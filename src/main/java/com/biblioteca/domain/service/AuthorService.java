package com.biblioteca.domain.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.domain.dto.Author.AuthorResponse;
import com.biblioteca.domain.dto.Author.AuthorSave;
import com.biblioteca.domain.dto.Author.AuthorUpdate;
import com.biblioteca.domain.repository.AuthorRepository;

@Service
public class AuthorService {
  @Autowired
  private AuthorRepository authorRepository;

  @Transactional(readOnly = true)
  public List<AuthorResponse> getAllAuthor() {
    return this.authorRepository.getAllAuthor();
  }

  @Transactional(readOnly = true)
  public AuthorResponse getAuthorById(int id) {
    return this.authorRepository.getAuthorById(id);
  }

  @Transactional(rollbackFor = Exception.class)
  public AuthorResponse saveAuthor(AuthorSave authorSave) {
    return this.authorRepository.saveAuthor(authorSave);
  }

  @Transactional(rollbackFor = Exception.class)
  public AuthorResponse updateAuthor(int id, AuthorUpdate authorUpdate) {
    return this.authorRepository.updateAuthor(id, authorUpdate);
  }

  @Transactional(rollbackFor = Exception.class)
  public void deleteAuthor(int id) {
    this.authorRepository.deleteAuthor(id);
  }

  @Transactional(readOnly = true)
  public List<AuthorResponse> findByFechaNacimientoBetween(LocalDate fechaInicio, LocalDate fechaFin) {
    return this.authorRepository.findByFechaNacimientoBetween(fechaInicio, fechaFin);
  }

  @Transactional(readOnly = true)
  public AuthorResponse findFirstNombre(String nombre) {
    return this.authorRepository.findFirstByNombre(nombre);
  }
}
