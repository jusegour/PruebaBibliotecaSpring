package com.ceiba.biblioteca.modelo.entidad;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Prestamo implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final int AFILIADO = 1;
    public static final int EMPLEADO = 2;
    public static final int INVITADO = 3;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String isbn;
    private String identificacionUsuario;
    private int tipoUsuario;
    private String fechaMaximaDevolucion;

    public Prestamo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIdentificacionUsuario() {
        return identificacionUsuario;
    }

    public void setIdentificacionUsuario(String identificacionUsuario) {
        this.identificacionUsuario = identificacionUsuario;
    }

    public String getFechaMaximaDevolucion() {
        return fechaMaximaDevolucion;
    }

    public void setFechaMaximaDevolucion(String fechaMaximaDevolucion) {
        this.fechaMaximaDevolucion = fechaMaximaDevolucion;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public String toString() {
        return "Prestamo{" + "id=" + id + ", isbn=" + isbn + ", identificacionUsuario=" + identificacionUsuario + ", tipoUsuario=" + tipoUsuario + ", fechaMaximaDevolucion=" + fechaMaximaDevolucion + '}';
    }

}
