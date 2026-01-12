package com.biblioteca.persistence.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Prestamo")
public class Prestamo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_prestamo")
  private Integer idPrestamo;

  @Column(name = "id_usuario", nullable = false)
  private Integer idUsuario;

  @Column(name = "fecha_prestamo", nullable = false)
  private LocalDate fechaPrestamo;

  @Column(nullable = false)
  private Integer cantidadLibros;

  @Column(nullable = false)
  private Boolean entregado;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_usuario", insertable = false, updatable = false)
  private Usuario usuario;

  @OneToMany(mappedBy = "prestamo")
  private Set<PrestamoLibro> prestamoLibros = new HashSet<>();

  public Integer getIdPrestamo() {
    return idPrestamo;
  }

  public void setIdPrestamo(Integer idPrestamo) {
    this.idPrestamo = idPrestamo;
  }

  public Integer getIdUsuario() {
    return idUsuario;
  }

  public void setIdUsuario(Integer idUsuario) {
    this.idUsuario = idUsuario;
  }

  public LocalDate getFechaPrestamo() {
    return fechaPrestamo;
  }

  public void setFechaPrestamo(LocalDate fechaPrestamo) {
    this.fechaPrestamo = fechaPrestamo;
  }

  public Integer getCantidadLibros() {
    return cantidadLibros;
  }

  public void setCantidadLibros(Integer cantidadLibros) {
    this.cantidadLibros = cantidadLibros;
  }

  public Boolean getEntregado() {
    return entregado;
  }

  public void setEntregado(Boolean entregado) {
    this.entregado = entregado;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public Set<PrestamoLibro> getPrestamoLibros() {
    return prestamoLibros;
  }

  public void setPrestamoLibros(Set<PrestamoLibro> prestamoLibros) {
    this.prestamoLibros = prestamoLibros;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
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
    Prestamo other = (Prestamo) obj;
    if (idPrestamo == null) {
      if (other.idPrestamo != null)
        return false;
    } else if (!idPrestamo.equals(other.idPrestamo))
      return false;
    return true;
  }

}
