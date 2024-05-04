/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.DTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author ASUS
 */
public class UsuarioAccesadoDTO implements Serializable{
    
    private int identificacion;
    private LocalDateTime horaEntrada;
    private LocalDate fechaEntrada;

    public UsuarioAccesadoDTO(int identificacion) {
        this.identificacion = identificacion;
        this.fechaEntrada = LocalDate.now();
        this.horaEntrada = LocalDateTime.now();
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }
    
}
