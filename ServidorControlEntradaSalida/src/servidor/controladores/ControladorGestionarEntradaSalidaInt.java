

package servidor.controladores;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import servidor.DTO.UsuarioEntradaSalidaDTO;

//Hereda de la clase Remote, lo cual la convierte en interfaz remota
public interface ControladorGestionarEntradaSalidaInt extends Remote
{
    //Definicion del primer método remoto
    public int registrarEntrada(int identificacion) throws RemoteException;
    //cada definición del método debe especificar que puede lanzar la excepción java.rmi.RemoteException
    
    //Definicion del segundo método remoto
    public int registrarSalida(int identificacion) throws RemoteException;
    
    public List<UsuarioEntradaSalidaDTO> consultarUsuariosAccesados() throws RemoteException;
}


