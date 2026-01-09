package com.biblioteca.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "prestamo_libro")

public class PrestamoLibro {
  @EmbeddedId
  private PrestamoLibroPK prestamoLibroPK;

  @Column(name = "cantidad_libros", nullable = false)
  private Integer cantidadLibros;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_libro", insertable = false, updatable = false)
  private Libro libro;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_prestamo", insertable = false, updatable = false)
  private Prestamo prestamo;

  public PrestamoLibroPK getPrestamoLibroPK() {
    return prestamoLibroPK;
  }

  public void setPrestamoLibroPK(PrestamoLibroPK prestamoLibroPK) {
    this.prestamoLibroPK = prestamoLibroPK;
  }

  public Integer getCantidadLibros() {
    return cantidadLibros;
  }

  public void setCantidadLibros(Integer cantidadLibros) {
    this.cantidadLibros = cantidadLibros;
  }

  public Libro getLibro() {
    return libro;
  }

  public void setLibro(Libro libro) {
    this.libro = libro;
  }

  public Prestamo getPrestamo() {
    return prestamo;
  }

  public void setPrestamo(Prestamo prestamo) {
    this.prestamo = prestamo;
  }

}
