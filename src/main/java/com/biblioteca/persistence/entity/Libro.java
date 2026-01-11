package com.biblioteca.persistence.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "libro")
public class Libro {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_libro")
  private Integer idLibro;

  @Column(nullable = false, length = 150, unique = true)
  private String titulo;

  @Column(name = "num_paginas", nullable = false)
  private Integer numPaginas;

  @Column(nullable = false, unique = true)
  private Long isbn;

  @Column(nullable = false)
  private Boolean disponible;
  // orphanRemoval --> "Si un hijo deja de pertenecer a su padre, ese hijo ya no
  // tiene razón de existir en el mundo (base de datos), con cascade solo surge
  // efecto si se elimina el padre por completo"
  @OneToMany(mappedBy = "libro", cascade = { CascadeType.ALL }, orphanRemoval = true)
  private List<LibroAutor> libroAutores;

  @OneToMany(mappedBy = "libro", cascade = { CascadeType.ALL }, orphanRemoval = true)
  private List<LibroCategoria> libroCategorias;

  @OneToMany(mappedBy = "libro")
  private List<PrestamoLibro> prestamoLibros;

  public Integer getIdLibro() {
    return idLibro;
  }

  public void setIdLibro(Integer idLibro) {
    this.idLibro = idLibro;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public Integer getNumPaginas() {
    return numPaginas;
  }

  public void setNumPaginas(Integer numPaginas) {
    this.numPaginas = numPaginas;
  }

  public Long getIsbn() {
    return isbn;
  }

  public void setIsbn(Long isbn) {
    this.isbn = isbn;
  }

  public Boolean getDisponible() {
    return disponible;
  }

  public void setDisponible(Boolean disponible) {
    this.disponible = disponible;
  }

  public List<LibroAutor> getLibroAutores() {
    return libroAutores;
  }

  public void setLibroAutores(List<LibroAutor> libroAutores) {
    this.libroAutores = libroAutores;
  }

  public List<LibroCategoria> getLibroCategorias() {
    return libroCategorias;
  }

  public void setLibroCategorias(List<LibroCategoria> libroCategorias) {
    this.libroCategorias = libroCategorias;
  }

  public List<PrestamoLibro> getPrestamoLibros() {
    return prestamoLibros;
  }

  public void setPrestamoLibros(List<PrestamoLibro> prestamoLibros) {
    this.prestamoLibros = prestamoLibros;
  }

}
