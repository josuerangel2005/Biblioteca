package com.biblioteca.persistence.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "libro_categoria")

public class LibroCategoria {
  @EmbeddedId
  private LibroCategoriaPK libroCategoriaPK;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("idLibro")
  @JoinColumn(name = "id_libro", insertable = false, updatable = false)
  private Libro libro;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("idCategoria")
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((libroCategoriaPK == null) ? 0 : libroCategoriaPK.hashCode());
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
    LibroCategoria other = (LibroCategoria) obj;
    if (libroCategoriaPK == null) {
      if (other.libroCategoriaPK != null)
        return false;
    } else if (!libroCategoriaPK.equals(other.libroCategoriaPK))
      return false;
    return true;
  }

}
