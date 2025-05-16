package com.ceiba.biblioteca.controlador;

import com.ceiba.biblioteca.modelo.entidad.Prestamo;
import com.ceiba.biblioteca.modelo.negocio.PrestamoServicio;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("prestamo")
public class PrestamoControlador {

    @Autowired
    PrestamoServicio servicioPrestamo;

    @PostMapping
    public ResponseEntity<Map<String, String>> post(@RequestBody Prestamo p) {
        Map<String, String> response = new HashMap<>();
        try {
            Prestamo responsePrestamo = servicioPrestamo.savePrestamo(p);

            response.put("id", "" + responsePrestamo.getId());
            response.put("fechaMaximaDevolucion", responsePrestamo.getFechaMaximaDevolucion());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("mensaje", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Prestamo>> get(@PathVariable Long id) {
        try {
            Optional<Prestamo> p = servicioPrestamo.findPrestamoById(id);
            return ResponseEntity.ok(p);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
