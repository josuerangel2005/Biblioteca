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

}
