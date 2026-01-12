package com.biblioteca.persistence.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "libro_autor")

public class LibroAutor {
  @EmbeddedId
  private LibroAutorPK libroAutorPK;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("idAutor")
  @JoinColumn(name = "id_autor", insertable = false, updatable = false)
  private Autor autor;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("idLibro")
  @JoinColumn(name = "id_libro", insertable = false, updatable = false)
  private Libro libro;

  public LibroAutorPK getLibroAutorPK() {
    return libroAutorPK;
  }

  public void setLibroAutorPK(LibroAutorPK libroAutorPK) {
    this.libroAutorPK = libroAutorPK;
  }

  public Autor getAutor() {
    return autor;
  }

  public void setAutor(Autor autor) {
    this.autor = autor;
  }

  public Libro getLibro() {
    return libro;
  }

  public void setLibro(Libro libro) {
    this.libro = libro;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((libroAutorPK == null) ? 0 : libroAutorPK.hashCode());
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
    LibroAutor other = (LibroAutor) obj;
    if (libroAutorPK == null) {
      if (other.libroAutorPK != null)
        return false;
    } else if (!libroAutorPK.equals(other.libroAutorPK))
      return false;
    return true;
  }

}
