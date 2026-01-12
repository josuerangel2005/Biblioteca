package com.biblioteca.persistence.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class LibroCategoriaPK implements Serializable {
  @Column(name = "id_libro")
  private Integer idLibro;

  @Column(name = "id_categoria")
  private Integer idCategoria;

  public Integer getIdLibro() {
    return idLibro;
  }

  public void setIdLibro(Integer idLibro) {
    this.idLibro = idLibro;
  }

  public Integer getIdCategoria() {
    return idCategoria;
  }

  public void setIdCategoria(Integer idCategoria) {
    this.idCategoria = idCategoria;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((idLibro == null) ? 0 : idLibro.hashCode());
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
    LibroCategoriaPK other = (LibroCategoriaPK) obj;
    if (idLibro == null) {
      if (other.idLibro != null)
        return false;
    } else if (!idLibro.equals(other.idLibro))
      return false;
    if (idCategoria == null) {
      if (other.idCategoria != null)
        return false;
    } else if (!idCategoria.equals(other.idCategoria))
      return false;
    return true;
  }

}
