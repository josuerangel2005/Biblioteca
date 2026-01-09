package com.biblioteca.persistence.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "libro_categoria")

public class LibroCategoria {
  @EmbeddedId
  private LibroCategoriaPK libroCategoriaPK;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_libro", insertable = false, updatable = false)
  private Libro libro;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_categoria", insertable = false, updatable = false)
  private Categoria categoria;

  public LibroCategoriaPK getLibroCategoriaPK() {
    return libroCategoriaPK;
  }

  public void setLibroCategoriaPK(LibroCategoriaPK libroCategoriaPK) {
    this.libroCategoriaPK = libroCategoriaPK;
  }

  public Libro getLibro() {
    return libro;
  }

  public void setLibro(Libro libro) {
    this.libro = libro;
  }

  public Categoria getCategoria() {
    return categoria;
  }

  public void setCategoria(Categoria categoria) {
    this.categoria = categoria;
  }

}
