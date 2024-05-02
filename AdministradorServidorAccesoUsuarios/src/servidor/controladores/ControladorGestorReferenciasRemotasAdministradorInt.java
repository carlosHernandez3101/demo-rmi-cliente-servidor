

package servidor.controladores;

import cliente.callback.ControladorCallbackInt;
import java.rmi.Remote;
import java.rmi.RemoteException;
//Hereda de la clase Remote, lo cual la convierte en interfaz remota
public interface ControladorGestorReferenciasRemotasAdministradorInt extends Remote
{
    //Definicion del primer método remoto
    public boolean registrarReferencia(ControladorCallbackInt referencia) throws RemoteException;
    //cada definición del método debe especificar que puede lanzar la excepción java.rmi.RemoteException
  
}


