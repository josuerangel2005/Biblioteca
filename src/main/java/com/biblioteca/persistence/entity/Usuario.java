package com.biblioteca.persistence.entity;

import java.time.LocalDate;
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
@Table(name = "usuario")

public class Usuario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_usuario")
  private Integer idUsuario;

  @Column(nullable = false, length = 150)
  private String nombre;

  @Column(nullable = false, length = 250)
  private String cedula;

  @Column(name = "fecha_nacimiento", nullable = false)
  private LocalDate fechaNacimiento;

  @Column(nullable = false)
  private String direccion;

  @OneToMany(mappedBy = "usuario")
  private Set<Prestamo> prestamos = new HashSet<>();

  public Integer getIdUsuario() {
    return idUsuario;
  }

  public void setIdUsuario(Integer idUsuario) {
    this.idUsuario = idUsuario;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getCedula() {
    return cedula;
  }

  public void setCedula(String cedula) {
    this.cedula = cedula;
  }

  public LocalDate getFechaNacimiento() {
    return fechaNacimiento;
  }

  public void setFechaNacimiento(LocalDate fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public Set<Prestamo> getPrestamos() {
    return prestamos;
  }

  public void setPrestamos(Set<Prestamo> prestamos) {
    this.prestamos = prestamos;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((idUsuario == null) ? 0 : idUsuario.hashCode());
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
    Usuario other = (Usuario) obj;
    if (idUsuario == null) {
      if (other.idUsuario != null)
        return false;
    } else if (!idUsuario.equals(other.idUsuario))
      return false;
    return true;
  }

}
