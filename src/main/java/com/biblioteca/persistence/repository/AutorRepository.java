package com.biblioteca.persistence.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.biblioteca.domain.dto.Author.AuthorResponse;
import com.biblioteca.domain.dto.Author.AuthorSave;
import com.biblioteca.domain.dto.Author.AuthorUpdate;
import com.biblioteca.domain.exception.AuthorAlreadyExistsException;
import com.biblioteca.domain.exception.AuthorNotExistsException;
import com.biblioteca.domain.repository.AuthorRepository;
import com.biblioteca.persistence.crud.AutorCrudRepository;
import com.biblioteca.persistence.entity.Autor;
import com.biblioteca.persistence.mapper.AuthorMapper;
import com.biblioteca.persistence.mapper.AuthorSaveMapper;
import com.biblioteca.persistence.mapper.AuthorUpdateMapper;

@Repository
public class AutorRepository implements AuthorRepository {

  @Autowired
  private AutorCrudRepository autorCrudRepository;

  @Autowired
  private AuthorMapper authorMapper;

  @Autowired
  private AuthorSaveMapper authorSaveMapper;

  @Autowired
  private AuthorUpdateMapper authorUpdateMapper;

  @Override
  public void deleteAuthor(int id) {
    this.autorCrudRepository.findById(id)
        .orElseThrow(() -> new AuthorNotExistsException(id));
    this.autorCrudRepository.deleteById(id);
  }

  @Override
  public List<AuthorResponse> getAllAuthor() {
    return this.authorMapper.toAuthors(this.autorCrudRepository.findAll());
  }

  @Override
  public AuthorResponse getAuthorById(int id) {
    return this.authorMapper.toAuthor(this.autorCrudRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Error al encontrar el autor con id " + id)));
  }

  @Override
  public AuthorResponse saveAuthor(AuthorSave author) {
    if (this.autorCrudRepository.findFirstByNombre(author.name()) != null)
      throw new AuthorAlreadyExistsException(author.name());
    return this.authorMapper.toAuthor(this.autorCrudRepository.save(this.authorSaveMapper.toAutor(author)));
  }

  @Override
  public AuthorResponse updateAuthor(int id, AuthorUpdate author) {
    Autor toUpdate = this.autorCrudRepository.findById(id)
        .orElseThrow(() -> new AuthorNotExistsException(id));
    this.authorUpdateMapper.updateEntityFromDto(author, toUpdate);
    return this.authorMapper.toAuthor(this.autorCrudRepository.save(toUpdate));
  }

  @Override
  public List<Autor> findByFechaNacimientoBetween(LocalDate fechaInicio, LocalDate fechaFin) {
    return this.autorCrudRepository.findByFechaNacimientoBetween(fechaInicio, fechaFin);
  }

  @Override
  public AuthorResponse findFirstByNombre(String nombre) {
    return this.authorMapper.toAuthor(this.autorCrudRepository.findFirstByNombre(nombre));
  }

}
