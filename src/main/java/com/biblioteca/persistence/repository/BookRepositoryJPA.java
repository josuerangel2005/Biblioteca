package com.biblioteca.persistence.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.biblioteca.domain.dto.Book.Book;
import com.biblioteca.domain.dto.Book.BookSave;
import com.biblioteca.domain.dto.Book.BookUpdate;
import com.biblioteca.domain.exception.AuthorNotExistsException;
import com.biblioteca.domain.exception.CategoryNotExistsException;
import com.biblioteca.domain.repository.BookRepository;
import com.biblioteca.persistence.crud.AutorCrudRepository;
import com.biblioteca.persistence.crud.CategoriaCrudRepository;
import com.biblioteca.persistence.crud.LibroCrudRepository;
import com.biblioteca.persistence.entity.Libro;
import com.biblioteca.persistence.mapper.BookMapper;
import com.biblioteca.persistence.mapper.BookSaveMapper;
import com.biblioteca.persistence.mapper.BookUpdateMapper;

import jakarta.persistence.EntityManager;

@Repository
public class BookRepositoryJPA implements BookRepository {
  @Autowired
  private AutorCrudRepository autorCrudRepository;

  @Autowired
  private CategoriaCrudRepository categoriaCrudRepository;

  @Autowired
  private LibroCrudRepository libroCrudRepository;

  @Autowired
  private BookMapper bookMapper;

  @Autowired
  private BookSaveMapper bookSaveMapper;

  @Autowired
  private BookUpdateMapper bookUpdateMapper;

  @Autowired
  private EntityManager entityManager;

  public void validarIdAutor(int id) {
    this.autorCrudRepository.findById(id).orElseThrow(() -> new AuthorNotExistsException(id));
  }

  public void validarIdCategoria(int id) {
    this.categoriaCrudRepository.findById(id).orElseThrow(() -> new CategoryNotExistsException(id));
  }

  @Override
  public void deleteBook(int id) {
    this.libroCrudRepository.findById(id).orElseThrow(() -> new RuntimeException());
    this.libroCrudRepository.deleteById(id);
  }

  @Override
  public List<Book> getAll() {
    return this.bookMapper.toBooks(this.libroCrudRepository.findAll());
  }

  @Override
  public Book getBookById(int id) {
    return this.bookMapper.toBook(this.libroCrudRepository.findById(id).orElseThrow(() -> new RuntimeException()));
  }

  @Override
  public Book saveBook(BookSave bookSave) {

    // Validar ids de categorias y autores
    bookSave.authors().forEach(id -> this.validarIdAutor(id.idAuthor()));
    bookSave.categories().forEach(id -> this.validarIdCategoria(id.categoryId()));

    Libro toSave = this.bookSaveMapper.toLibro(bookSave);
    toSave.setDisponible(true);

    // setear tanto a los libroAuto y libroCategoria el libro actual a guardar para
    // que maps id sepa cual id libroCategoria
    toSave.getLibroAutores().forEach(libroAutor -> libroAutor.setLibro(toSave));
    toSave.getLibroCategorias().forEach(libroCategoria -> libroCategoria.setLibro(toSave));

    Libro libro = this.libroCrudRepository.save(toSave);

    this.entityManager.flush();
    this.entityManager.clear();

    return this.bookMapper
        .toBook(this.libroCrudRepository.findById(libro.getIdLibro()).orElseThrow(() -> new RuntimeException()));
  }

  @Override
  public Book updateBook(BookUpdate bookUpdate, int id) {
    bookUpdate.authors().forEach(idA -> this.validarIdAutor(idA.idAuthor()));
    bookUpdate.categories().forEach(idC -> this.validarIdCategoria(idC.categoryId()));

    Libro toUpdate = this.libroCrudRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

    // 3. Limpiar las relaciones actuales para evitar el error de "duplicados"
    toUpdate.getLibroAutores().clear();
    toUpdate.getLibroCategorias().clear();
    this.entityManager.flush();

    // 4. Mapear los nuevos datos del DTO a la entidad
    this.bookUpdateMapper.updateLibroFromBookSave(bookUpdate, toUpdate);

    // 5. Re-vincular las relaciones (id libro -> tabla intermedia)
    toUpdate.getLibroAutores().forEach(la -> la.setLibro(toUpdate));
    toUpdate.getLibroCategorias().forEach(lc -> lc.setLibro(toUpdate));

    Libro libroGuardado = this.libroCrudRepository.save(toUpdate);

    // El flush escribe en las tablas intermedias, el refresh trae los nombres
    this.entityManager.flush();
    this.entityManager.refresh(libroGuardado);

    return this.bookMapper.toBook(libroGuardado);
  }

}
