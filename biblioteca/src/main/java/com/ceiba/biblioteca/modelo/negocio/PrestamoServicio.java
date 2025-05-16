package com.ceiba.biblioteca.modelo.negocio;

import com.ceiba.biblioteca.modelo.crud.ICrudPrestamoRepository;
import com.ceiba.biblioteca.modelo.entidad.Prestamo;
import com.ceiba.biblioteca.util.Utils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrestamoServicio {

    @Autowired
    ICrudPrestamoRepository crudPrestamo;

    public Prestamo savePrestamo(Prestamo prestamo) throws Exception {
        Date fechaActual = new Date();
        Date fechaDevolucion = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        List<Prestamo> prestamoEncontrado = findByIdentificacion(prestamo.getIdentificacionUsuario());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual);

        if (prestamo.getIsbn() != null) {
            if (prestamo.getIsbn().length() > 10) {
                throw new Exception("El codigo Isbn no debe tener mas de 10 caracteres");
            }
        }

        if (prestamo.getIdentificacionUsuario() != null) {
            if (prestamo.getIdentificacionUsuario().length() > 10) {
                throw new Exception("La identificacion del usuario no debe tener mas de 10 caracteres");
            }
        }

        if (prestamo.getTipoUsuario() != 1 && prestamo.getTipoUsuario() != 2
                && prestamo.getTipoUsuario() != 3) {
            throw new Exception("Tipo de usuario no permitido en la biblioteca");
        }

        if (prestamoEncontrado.size() > 0) {
            if (prestamoEncontrado.get(0).getTipoUsuario() == Prestamo.INVITADO) {
                throw new Exception("El usuario con identificaci\u00F3n " + prestamo.getIdentificacionUsuario()
                        + " ya tiene un libro prestado por lo cual no se le puede realizar otro pr\u00E9stamo");
            }
        }

        if (prestamo.getTipoUsuario() == Prestamo.AFILIADO) {
            fechaDevolucion = Utils.sumarDiasHabiles(fechaActual, 10);
        }

        if (prestamo.getTipoUsuario() == Prestamo.EMPLEADO) {
            fechaDevolucion = Utils.sumarDiasHabiles(fechaActual, 8);
        }

        if (prestamo.getTipoUsuario() == Prestamo.INVITADO) {
            fechaDevolucion = Utils.sumarDiasHabiles(fechaActual, 7);
        }

        prestamo.setFechaMaximaDevolucion(formato.format(fechaDevolucion));

        return crudPrestamo.save(prestamo);

    }

    public Optional<Prestamo> findPrestamoById(Long id) throws Exception {
        Optional<Prestamo> u = crudPrestamo.findById(id);

        if (u != null) {
            return u;
        } else {
            throw new Exception("Prestamo con id: " + id + " no existe");
        }
    }

    public List<Prestamo> findByIdentificacion(String identificacion) {
        return crudPrestamo.findByIdentificacionUsuario(identificacion);
    }

}
