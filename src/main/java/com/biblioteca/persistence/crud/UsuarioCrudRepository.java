package com.biblioteca.persistence.crud;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biblioteca.persistence.entity.Usuario;
import com.biblioteca.persistence.projection.Usuarios;

public interface UsuarioCrudRepository extends JpaRepository<Usuario, Integer> {
  Usuario findFirstByNombre(String nombre);

  @Query(value = """
      SELECT
          u.nombre AS nombre,
          u.cedula AS cedula,
          COUNT(p.id_usuario) AS totalPrestamo
       FROM
          usuario u INNER JOIN prestamo p USING(id_usuario)
      WHERE
          p.fecha_prestamo BETWEEN :date1 AND :date2
      GROUP BY
          1,
          2
      ORDER BY
          3
          DESC
      LIMIT 3
      """, nativeQuery = true)
  List<Usuarios> getTop3Users(@Param("date1") LocalDateTime date1, @Param("date2") LocalDateTime date2);
}
