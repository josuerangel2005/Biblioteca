package com.biblioteca.persistence.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class PrestamoLibroPK implements Serializable {
  @Column(name = "id_libro")
  private Integer idLibro;

  @Column(name = "id_prestamo")
  private Integer idPrestamo;

  public Integer getIdLibro() {
    return idLibro;
  }

  public void setIdLibro(Integer idLibro) {
    this.idLibro = idLibro;
  }

  public Integer getIdPrestamo() {
    return idPrestamo;
  }

  public void setIdPrestamo(Integer idPrestamo) {
    this.idPrestamo = idPrestamo;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((idLibro == null) ? 0 : idLibro.hashCode());
    result = prime * result + ((idPrestamo == null) ? 0 : idPrestamo.hashCode());
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
    PrestamoLibroPK other = (PrestamoLibroPK) obj;
    if (idLibro == null) {
      if (other.idLibro != null)
        return false;
    } else if (!idLibro.equals(other.idLibro))
      return false;
    if (idPrestamo == null) {
      if (other.idPrestamo != null)
        return false;
    } else if (!idPrestamo.equals(other.idPrestamo))
      return false;
    return true;
  }

}
