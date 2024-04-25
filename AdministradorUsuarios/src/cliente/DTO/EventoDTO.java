/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cliente.DTO;
import java.io.Serializable;
/**
 *
 * @author David
 */
public class EventoDTO implements Serializable{
    private final String mensaje;
    private final String accion;
    
    public EventoDTO(String mensaje, String accion){
        this.mensaje = mensaje;
        this.accion = accion;
    }
    
    public String getMensaje(){
        return mensaje;
    }
    public String getAccion(){
        return accion;
    }
}
