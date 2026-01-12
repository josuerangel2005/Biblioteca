package com.biblioteca.persistence.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class LibroAutorPK implements Serializable {
  @Column(name = "id_autor")
  private Integer idAutor;

  @Column(name = "id_libro")
  private Integer idLibro;

  public Integer getIdAutor() {
    return idAutor;
  }

  public void setIdAutor(Integer idAutor) {
    this.idAutor = idAutor;
  }

  public Integer getIdLibro() {
    return idLibro;
  }

  public void setIdLibro(Integer idLibro) {
    this.idLibro = idLibro;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((idAutor == null) ? 0 : idAutor.hashCode());
    result = prime * result + ((idLibro == null) ? 0 : idLibro.hashCode());
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
    LibroAutorPK other = (LibroAutorPK) obj;
    if (idAutor == null) {
      if (other.idAutor != null)
        return false;
    } else if (!idAutor.equals(other.idAutor))
      return false;
    if (idLibro == null) {
      if (other.idLibro != null)
        return false;
    } else if (!idLibro.equals(other.idLibro))
      return false;
    return true;
  }

}
