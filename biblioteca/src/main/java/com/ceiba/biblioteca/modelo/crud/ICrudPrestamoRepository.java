
package com.ceiba.biblioteca.modelo.crud;

import com.ceiba.biblioteca.modelo.entidad.Prestamo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author JUAN S GOMEZ URIBE
 */
@Repository
public interface ICrudPrestamoRepository extends JpaRepository<Prestamo, Long> {
    List<Prestamo> findByIdentificacionUsuario(String identificacionUsuario);
}
