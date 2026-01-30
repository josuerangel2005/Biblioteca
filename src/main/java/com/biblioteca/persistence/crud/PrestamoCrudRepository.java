package com.biblioteca.persistence.crud;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biblioteca.persistence.entity.Prestamo;
import com.biblioteca.persistence.projection.Reporte;

public interface PrestamoCrudRepository extends JpaRepository<Prestamo, Integer> {
  Prestamo findFirstByIdUsuarioAndFechaPrestamo(Integer idUsuario, LocalDateTime fechaPrestamo);

  List<Prestamo> findByEntregadoFalse();

  List<Prestamo> findByEntregadoFalseAndIdUsuario(int idUsuario);

  Long countByEntregadoFalseAndIdUsuario(int idUsuario);

  @Query(value = """
      SELECT
            p
      FROM
            Prestamo p JOIN FETCH p.usuario u
      WHERE
          p.entregado = false
      """)
  List<Prestamo> getAllNotDelivered();

  @Query(value = """
        SELECT
              p
        FROM
             p LEFT JOIN FETCH p.prestamoLibros pl
             LEFT JOIN FETCH pl.libro l
        WHERE
            p.idPrestamo = :loanId
      """)
  Optional<Prestamo> getPrestamoByIdWithBooks(@Param("loanId") int loanId);

  @Query(value = """
          SELECT
              TO_CHAR(p.fecha_prestamo, 'YYYY-MM') AS fecha,
              COUNT(*) AS totalPrestamos,
              SUM(CASE WHEN p.entregado = True THEN 1 ELSE 0 END) AS totalDevueltos
          FROM
              prestamo p
          WHERE
              p.fecha_prestamo BETWEEN DATE_TRUNC('month',NOW() - INTERVAL '6 months') AND NOW()
          GROUP BY
              1
          ORDER BY
              1
                ASC
      """, nativeQuery = true)
  List<Reporte> getReportes();
}
