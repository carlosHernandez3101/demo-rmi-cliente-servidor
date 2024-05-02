/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.servicios;


import servidor.utilidades.UtilidadesRegistroS;
import servidor.utilidades.UtilidadesConsola;
import java.rmi.RemoteException;
import servidor.Repositorios.LoginRepository;
import servidor.Repositorios.UsuariosRepository;
import servidor.controladores.ControladorGestorCredencialesUsuariosImpl;
import servidor.controladores.ControladorGestorUsuariosEntradaSalidaImpl;

public class ServidorDeObjetos
{
    public static void main(String args[]) throws RemoteException
    {        
         
        int numPuertoRMIRegistry = 0;
        String direccionIpRMIRegistry = "";
                       
        System.out.println("Cual es el la dirección ip donde se encuentra  el rmiRegistry ");
        direccionIpRMIRegistry = UtilidadesConsola.leerCadena();
        System.out.println("Cual es el número de puerto por el cual escucha el rmiRegistry ");
        numPuertoRMIRegistry = UtilidadesConsola.leerEntero(); 
     
        UsuariosRepository objRepository = new UsuariosRepository();
        LoginRepository objLoginRepository = new LoginRepository();
        
        ControladorGestorUsuariosEntradaSalidaImpl objRemoto = new ControladorGestorUsuariosEntradaSalidaImpl(objRepository);//se leasigna el puerto de escucha del objeto remoto
        ControladorGestorCredencialesUsuariosImpl objRemoto2 = new ControladorGestorCredencialesUsuariosImpl(objLoginRepository);
        
        try
        {
           UtilidadesRegistroS.arrancarNS(numPuertoRMIRegistry);
           UtilidadesRegistroS.RegistrarObjetoRemoto(objRemoto, direccionIpRMIRegistry, numPuertoRMIRegistry, "objServicioGestionUsuariosEntradaSalida");
           UtilidadesRegistroS.RegistrarObjetoRemoto(objRemoto2, direccionIpRMIRegistry, numPuertoRMIRegistry, "objServicioGestionCredencialesUsuario");
           
        } catch (RemoteException e)
        {
            System.err.println("No fue posible Arrancar el NS o Registrar el objeto remoto" +  e.getMessage());
        }       
        
    }
}
