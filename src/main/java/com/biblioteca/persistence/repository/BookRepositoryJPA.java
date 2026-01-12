package com.biblioteca.persistence.repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
import com.biblioteca.persistence.entity.Autor;
import com.biblioteca.persistence.entity.Categoria;
import com.biblioteca.persistence.entity.Libro;
import com.biblioteca.persistence.entity.LibroAutor;
import com.biblioteca.persistence.entity.LibroAutorPK;
import com.biblioteca.persistence.entity.LibroCategoria;
import com.biblioteca.persistence.entity.LibroCategoriaPK;
import com.biblioteca.persistence.mapper.BookMapper;
import com.biblioteca.persistence.mapper.BookSaveMapper;
import com.biblioteca.persistence.mapper.BookUpdateMapper;

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
    // validar ids de categorias y autores

    bookSave.authors().forEach(id -> this.validarIdAutor(id.idAuthor()));
    bookSave.categories().forEach(id -> this.validarIdCategoria(id.categoryId()));

    Libro libro = this.bookSaveMapper.toLibro(bookSave);
    libro.setDisponible(true);

    // setear los autores y categorias (como objetos) para que jpa tome el id a
    // treavés del objeto
    Set<LibroAutor> libroAutors = bookSave.authors().stream().map(authorId -> {
      Autor autor = this.autorCrudRepository.findById(authorId.idAuthor()).orElseThrow(() -> new RuntimeException());

      LibroAutor libroAutor = new LibroAutor();

      libroAutor.setLibroAutorPK(new LibroAutorPK());

      libroAutor.setAutor(autor); // le damos el autor como objeto para que en libroAutor tome como id el id del
                                  // autor
      libroAutor.setLibro(libro); // le seteamos el libro para que la anotación @mapsid sepa de que objeto tomar
                                  // el id para propagar en libroAutor en el campo de idLibro
      return libroAutor;

    }).collect(Collectors.toSet());

    Set<LibroCategoria> libroCategorias = bookSave.categories().stream().map(categoryId -> {
      Categoria categoria = this.categoriaCrudRepository.findById(categoryId.categoryId())
          .orElseThrow(() -> new RuntimeException());

      LibroCategoria libroCategoria = new LibroCategoria();

      libroCategoria.setLibroCategoriaPK(new LibroCategoriaPK());

      libroCategoria.setCategoria(categoria);
      libroCategoria.setLibro(libro);

      return libroCategoria;
    }).collect(Collectors.toSet());

    libro.setLibroAutores(libroAutors);
    libro.setLibroCategorias(libroCategorias);

    return this.bookMapper.toBook(this.libroCrudRepository.save(libro));
  }

  @Override
  public Book updateBook(BookUpdate bookUpdate, int id) {
    Libro libro = this.libroCrudRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

    this.bookUpdateMapper.updateLibroFromBookSave(bookUpdate, libro);

    Set<Integer> nuevosIdsAutores = bookUpdate.authors().stream()
        .map(a -> a.idAuthor())
        .collect(Collectors.toSet());

    libro.getLibroAutores().removeIf(ra -> !nuevosIdsAutores.contains(ra.getAutor().getIdAutor()));

    bookUpdate.authors().forEach(dtoAuthor -> {
      boolean yaExiste = libro.getLibroAutores().stream()
          .anyMatch(ra -> ra.getAutor().getIdAutor().equals(dtoAuthor.idAuthor()));

      if (!yaExiste) {
        this.validarIdAutor(dtoAuthor.idAuthor());
        Autor autor = this.autorCrudRepository.findById(dtoAuthor.idAuthor()).orElseThrow();

        LibroAutor libroAutor = new LibroAutor();
        libroAutor.setLibroAutorPK(new LibroAutorPK());
        libroAutor.setLibro(libro);
        libroAutor.setAutor(autor);

        libro.getLibroAutores().add(libroAutor);
      }
    });

    Set<Integer> nuevosIdsCategorias = bookUpdate.categories().stream()
        .map(c -> c.categoryId())
        .collect(Collectors.toSet());

    libro.getLibroCategorias().removeIf(lc -> !nuevosIdsCategorias.contains(lc.getCategoria().getIdCategoria()));

    bookUpdate.categories().forEach(dtoCategory -> {
      boolean yaExiste = libro.getLibroCategorias().stream()
          .anyMatch(lc -> lc.getCategoria().getIdCategoria().equals(dtoCategory.categoryId()));

      if (!yaExiste) {
        this.validarIdCategoria(dtoCategory.categoryId());
        Categoria categoria = this.categoriaCrudRepository.findById(dtoCategory.categoryId()).orElseThrow();

        LibroCategoria libroCategoria = new LibroCategoria();
        libroCategoria.setLibroCategoriaPK(new LibroCategoriaPK());
        libroCategoria.setLibro(libro);
        libroCategoria.setCategoria(categoria);

        libro.getLibroCategorias().add(libroCategoria);
      }
    });

    Libro libroGuardado = this.libroCrudRepository.save(libro);
    return this.bookMapper.toBook(libroGuardado);
  }
}
