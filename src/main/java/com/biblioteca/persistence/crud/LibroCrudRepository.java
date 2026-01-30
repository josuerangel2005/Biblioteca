package com.biblioteca.persistence.crud;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.biblioteca.domain.dto.UpdatePageBookDto;
import com.biblioteca.persistence.entity.Libro;
import com.biblioteca.persistence.projection.LibroVeces;

public interface LibroCrudRepository extends JpaRepository<Libro, Integer> {
  Libro findFirstByTitulo(String titulo);

  List<Libro> findByDisponibleTrue();

  List<Libro> findByTituloContainingIgnoreCase(String titulo);

  List<Libro> findByNumPaginasGreaterThan(int numPaginas);

  @Query(value = """
      SELECT
              DISTINCT l
      FROM
              Libro l LEFT JOIN FETCH l.librosAutores la
              LEFT JOIN FETCH la.autor
      """)
  List<Libro> librosAutores();

  @Query(value = """
      SELECT
            l
      FROM
            Libro l JOIN l.libroCategorias lc
            JOIN lc.categoria c
      WHERE
          c.nombre = :categoryName
      """)
  List<Libro> librosByCategoria(@Param("categoryName") String categoryName);

  @Query(value = """
        SELECT
              l.titulo,
              COUNT(pl.id_libro) AS quantity
        FROM
              libro l INNER JOIN prestamo_libro pl ON l.id_libro = pl.id_libro
        GROUP BY
              l.titulo
        ORDER BY
              quantity DESC
        LIMIT
              5
      """, nativeQuery = true)
  List<LibroVeces> getMostLoanBooks();

  @Query(value = """
          SELECT
                *
          FROM
              libro l
          WHERE NOT EXISTS(
            SELECT
                  1
            FROM
                  prestamo_libro lp
            WHERE
                  lp.id_libro = l.id_libro
      )
        """, nativeQuery = true)
  List<Libro> noLoanBooks();

  @Modifying
  @Query(value = """
        UPDATE libro SET num_paginas = :#{#updatePageBookDto.pages} WHERE libro.id_libro = :#{#updatePageBookDto.bookId}
      """, nativeQuery = true)
  void updatePagesOfBook(@Param("updatePageBookDto") UpdatePageBookDto updatePageBookDto);

  @Procedure(value = "P_ACTUALIZAR_DISPONIBILIDAD_LIBRO", outputParameterName = "state")
  Boolean updateStateOfBook(@Param("p_id_libro") int idLibro);
}
