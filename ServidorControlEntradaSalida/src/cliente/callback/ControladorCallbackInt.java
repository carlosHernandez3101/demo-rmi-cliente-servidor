/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cliente.callback;

import cliente.DTO.EventoDTO;
import java.rmi.Remote;
import java.rmi.RemoteException;
import servidor.DTO.UsuarioEntradaSalidaDTO;

/**
 *
 * @author ASUS
 */
public interface ControladorCallbackInt extends Remote
{
    public void notificar(EventoDTO objEvento, UsuarioEntradaSalidaDTO objUsuarioDTO) throws RemoteException;
    
}
