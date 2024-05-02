package servidor.DTO;

import java.io.Serializable;
import java.time.LocalDate;

public class UsuarioEntradaSalidaDTO implements Serializable
{	
    private int identificacion;
    private String nombre;
    private String apellidos;
    private Rol rol;
    private LocalDate fechaRegistro;

    public enum Rol {
        ADMINISTRATIVO, DOCENTE, ESTUDIANTE
    }

    public UsuarioEntradaSalidaDTO(int identificacion, String nombre, String apellidos, Rol rol, LocalDate fechaRegistro) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaRegistro = fechaRegistro;
        this.rol = rol;
    }
     
    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }
    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    public Rol getRol() {
        return rol;
    }
    public void setRol(Rol rol) {
        this.rol = rol;
    }  
}
