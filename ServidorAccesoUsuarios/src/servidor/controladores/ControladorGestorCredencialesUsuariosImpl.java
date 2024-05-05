/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.controladores;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import servidor.DTO.LoginDTO;
import servidor.Repositorios.LoginRepositoryInt;

/**
 *
 * @author ASUS
 */
public class ControladorGestorCredencialesUsuariosImpl extends UnicastRemoteObject implements ControladorGestorCredencialesUsuariosInt{
    
     private final LoginRepositoryInt objLoginRepositoryInt;

    public ControladorGestorCredencialesUsuariosImpl(LoginRepositoryInt objLoginRepositoryInt) throws RemoteException {
        super();
        this.objLoginRepositoryInt = objLoginRepositoryInt;
    }

    @Override
    public boolean iniciarSesion(LoginDTO objLogin) throws RemoteException {
        System.out.println("\nIniciando proceso de validacion de credenciales..." );
        return this.objLoginRepositoryInt.iniciarSesion(objLogin);
    }
     
     
    
}
