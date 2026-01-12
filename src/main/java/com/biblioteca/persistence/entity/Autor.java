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
@Table(name = "autores")
public class Autor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_autor")
  private Integer idAutor;

  @Column(nullable = false, length = 250)
  private String nombre;

  @Column(nullable = false, length = 560)
  private String biografia;

  @Column(nullable = false, length = 150)
  private String nacionalidad;

  @Column(name = "fecha_nacimiento", nullable = false)
  private LocalDate fechaNacimiento;

  @OneToMany(mappedBy = "autor")
  private Set<LibroAutor> libroAutors = new HashSet<>();

  public Integer getIdAutor() {
    return idAutor;
  }

  public void setIdAutor(Integer idAutor) {
    this.idAutor = idAutor;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getBiografia() {
    return biografia;
  }

  public void setBiografia(String biografia) {
    this.biografia = biografia;
  }

  public String getNacionalidad() {
    return nacionalidad;
  }

  public void setNacionalidad(String nacionalidad) {
    this.nacionalidad = nacionalidad;
  }

  public LocalDate getFechaNacimiento() {
    return fechaNacimiento;
  }

  public void setFechaNacimiento(LocalDate fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }

  public Set<LibroAutor> getLibroAutors() {
    return libroAutors;
  }

  public void setLibroAutors(Set<LibroAutor> libroAutors) {
    this.libroAutors = libroAutors;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((idAutor == null) ? 0 : idAutor.hashCode());
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
    Autor other = (Autor) obj;
    if (idAutor == null) {
      if (other.idAutor != null)
        return false;
    } else if (!idAutor.equals(other.idAutor))
      return false;
    return true;
  }

}
