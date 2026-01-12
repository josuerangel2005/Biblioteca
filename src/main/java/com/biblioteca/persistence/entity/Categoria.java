package com.biblioteca.persistence.entity;

import java.util.HashSet;
import java.util.Set;

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
  private Set<LibroCategoria> libroCategorias = new HashSet<>();

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

  public Set<LibroCategoria> getLibroCategorias() {
    return libroCategorias;
  }

  public void setLibroCategorias(Set<LibroCategoria> libroCategorias) {
    this.libroCategorias = libroCategorias;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((idCategoria == null) ? 0 : idCategoria.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Categoria other = (Categoria) obj;
    if (idCategoria == null) {
      if (other.idCategoria != null)
        return false;
    } else if (!idCategoria.equals(other.idCategoria))
      return false;
    return true;
  }

}
