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

}
