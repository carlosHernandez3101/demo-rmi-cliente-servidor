/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cliente.callback;

import cliente.DTO.EventoDTO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author David
 */
public class ControladorCallbackImpl extends UnicastRemoteObject implements ControladorCallbackInt{

    public ControladorCallbackImpl() throws RemoteException{
        super();
    }
    @Override
    public void notificar(EventoDTO objEvento) throws RemoteException {
        System.out.println("Mensaje: " + objEvento.getMensaje());
        System.out.println("Accion del usuario:" + objEvento.getAccion());
    }    
}
