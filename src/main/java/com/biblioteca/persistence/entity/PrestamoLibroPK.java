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

}
