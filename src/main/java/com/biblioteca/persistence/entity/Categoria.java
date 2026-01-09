package com.biblioteca.persistence.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categoria")
public class Categoria {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_categoria")
  private Integer idCategoria;

  @Column(nullable = false, length = 150)
  private String nombre;

  @OneToMany(mappedBy = "categoria")
  private List<LibroCategoria> libroCategorias;

  public Integer getIdCategoria() {
    return idCategoria;
  }

  public void setIdCategoria(Integer idCategoria) {
    this.idCategoria = idCategoria;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public List<LibroCategoria> getLibroCategorias() {
    return libroCategorias;
  }

  public void setLibroCategorias(List<LibroCategoria> libroCategorias) {
    this.libroCategorias = libroCategorias;
  }

}
