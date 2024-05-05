

package servidor.controladores;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import servidor.DTO.UsuarioEntradaSalidaDTO;
import servidor.Repositorios.LoginRepositoryInt;
import servidor.Repositorios.UsuariosRepositoryInt;

public class ControladorGestorUsuariosEntradaSalidaImpl extends UnicastRemoteObject implements ControladorGestorUsuariosEntradaSalidaInt{
    
    private final UsuariosRepositoryInt objUsuariosEntradaSalidaRepository;

    public ControladorGestorUsuariosEntradaSalidaImpl(UsuariosRepositoryInt objProductosRepository) throws RemoteException
    {
        super(); //se asigna un puerto de escucha al OR
        this.objUsuariosEntradaSalidaRepository=objProductosRepository;
    }    

    @Override
    public boolean registrarUsuarioEntradaSalida(UsuarioEntradaSalidaDTO objProducto) throws RemoteException {
        System.out.println("\nIniciando proceso de registro de usuario...");
        return this.objUsuariosEntradaSalidaRepository.registrarUsuarioEntradaSalida(objProducto);
    }

    @Override
    public int consultarCantidadUsuariosEntradaSalida() throws RemoteException {
        System.out.println("\nIniciando proceso de consulta de numero de usuarios...");
        return this.objUsuariosEntradaSalidaRepository.consultarCantidadUsuariosEntradaSalida();
    }

    @Override
    public UsuarioEntradaSalidaDTO consultarUsuarioEntradaSalida(int identificacion) throws RemoteException {
        System.out.println("\nIniciando proceso de consulta de usuario...");
        return this.objUsuariosEntradaSalidaRepository.consultarUsuarioEntradaSalida(identificacion);
    }

    @Override
    public List<UsuarioEntradaSalidaDTO> listarUsuariosEntradaSalida() throws RemoteException {
        System.out.println("\nIniciando proceso de obtencion de listado de usuarios...");
        return this.objUsuariosEntradaSalidaRepository.listarUsuariosEntradaSalida();
    }

    @Override
    public boolean eliminarUsuarioEntradaSalida(int identificacion) throws RemoteException {
        System.out.println("\nIniciando proceso de eliminacion de usuario...");
        return this.objUsuariosEntradaSalidaRepository.eliminarUsuariosEntradaSalida(identificacion);
    }    
    
}
