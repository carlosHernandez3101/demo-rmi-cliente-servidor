

package servidor.controladores;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import servidor.DTO.LoginDTO;
import servidor.DTO.UsuarioEntradaSalidaDTO;
//Hereda de la clase Remote, lo cual la convierte en interfaz remota
public interface ControladorGestorUsuariosEntradaSalidaInt extends Remote
{
    //Definicion del primer método remoto
    public boolean registrarUsuarioEntradaSalida(UsuarioEntradaSalidaDTO objProducto) throws RemoteException;
    //cada definición del método debe especificar que puede lanzar la excepción java.rmi.RemoteException
    
    //Definicion del segundo método remoto
    public int consultarCantidadUsuariosEntradaSalida() throws RemoteException; 
    //cada definición del método debe especificar que puede lanzar la excepción java.rmi.RemoteException
    
    //Definicion del tercer método remoto
    public UsuarioEntradaSalidaDTO consultarUsuarioEntradaSalida(int identificacion) throws RemoteException; 
    //cada definición del método debe especificar que puede lanzar la excepción java.rmi.RemoteException
   
    //Definicion del cuarto método remoto
    public List<UsuarioEntradaSalidaDTO> listarUsuariosEntradaSalida()throws RemoteException;
     //cada definición del método debe especificar que puede lanzar la excepción java.rmi.RemoteException   
    
    public boolean eliminarUsuarioEntradaSalida(int identificacion) throws RemoteException;
  
}


