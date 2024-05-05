/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cliente.callback;

import cliente.DTO.EventoDTO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import servidor.DTO.UsuarioEntradaSalidaDTO;

/**
 *
 * @author David
 */
public class ControladorCallbackImpl extends UnicastRemoteObject implements ControladorCallbackInt {

    public ControladorCallbackImpl() throws RemoteException {
        super();
    }

    @Override
    public void notificar(EventoDTO objEvento, UsuarioEntradaSalidaDTO objUsuarioDTO) throws RemoteException {
        System.out.printf("\n%-10s ","NUEVA NOTIFICACION");
        if (objUsuarioDTO != null) {
            System.out.printf("\n\n%-10s  %-15s  %-10s \n", "ID", "Nombres", "Apellidos");
            System.out.printf("%-10s  %-15s  %-10s \n", objUsuarioDTO.getIdentificacion(), objUsuarioDTO.getNombre(), objUsuarioDTO.getApellidos());
        }
        System.out.println("\nMensaje: " + objEvento.getMensaje());
        System.out.println("Accion del usuario:" + objEvento.getAccion());
    }
}
