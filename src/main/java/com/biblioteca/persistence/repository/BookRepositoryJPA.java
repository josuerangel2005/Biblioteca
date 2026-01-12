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

      LibroAutorPK libroAutorPK = new LibroAutorPK();
      libroAutorPK.setIdAutor(autor.getIdAutor());
      libroAutor.setLibroAutorPK(libroAutorPK);

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

      LibroCategoriaPK libroCategoriaPK = new LibroCategoriaPK();
      libroCategoriaPK.setIdCategoria(categoria.getIdCategoria());
      libroCategoria.setLibroCategoriaPK(libroCategoriaPK);

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
    bookUpdate.authors().forEach(idAuthor -> this.validarIdAutor(idAuthor.idAuthor()));
    bookUpdate.categories().forEach(categoryId -> this.validarIdCategoria(categoryId.categoryId()));

    Libro libro = this.libroCrudRepository.findById(id).orElseThrow(() -> new RuntimeException());

    this.bookUpdateMapper.updateLibroFromBookSave(bookUpdate, libro);

    libro.getLibroAutores().clear();
    libro.getLibroCategorias().clear();

    // Mapear y AÑADIR (addAll) en lugar de setear una lista nueva, orphan da error
    // se seteamos una nueva lista, debe ser la misma referencia
    bookUpdate.authors().forEach(idAuthor -> {
      Autor autor = this.autorCrudRepository.findById(idAuthor.idAuthor()).orElseThrow();

      LibroAutor libroAutor = new LibroAutor();
      LibroAutorPK libroAutorPK = new LibroAutorPK();
      libroAutorPK.setIdAutor(autor.getIdAutor());
      libroAutorPK.setIdLibro(libro.getIdLibro());

      libroAutor.setLibroAutorPK(libroAutorPK);
      libroAutor.setLibro(libro);
      libroAutor.setAutor(autor);

      libro.getLibroAutores().add(libroAutor);
    });

    bookUpdate.categories().forEach(idCat -> {
      Categoria categoria = this.categoriaCrudRepository.findById(idCat.categoryId()).orElseThrow();

      LibroCategoria libroCategoria = new LibroCategoria();
      LibroCategoriaPK libroCategoriaPK = new LibroCategoriaPK();
      libroCategoriaPK.setIdCategoria(categoria.getIdCategoria());
      libroCategoriaPK.setIdLibro(libro.getIdLibro());

      libroCategoria.setLibroCategoriaPK(libroCategoriaPK);
      libroCategoria.setLibro(libro);
      libroCategoria.setCategoria(categoria);

      libro.getLibroCategorias().add(libroCategoria);
    });

    return this.bookMapper.toBook(this.libroCrudRepository.save(libro));
  }
}
