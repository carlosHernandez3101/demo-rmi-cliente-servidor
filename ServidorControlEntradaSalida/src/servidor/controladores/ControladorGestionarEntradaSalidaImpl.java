package servidor.controladores;

import cliente.DTO.EventoDTO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import servidor.DTO.UsuarioEntradaSalidaDTO;
import servidor.Repositorios.EntradasRepositoryInt;

public class ControladorGestionarEntradaSalidaImpl extends UnicastRemoteObject implements ControladorGestionarEntradaSalidaInt{
    
    private final EntradasRepositoryInt objEntradasRepository;
    private final ControladorGestorUsuariosEntradaSalidaInt objRemotoServidorUsuarios;
    private final ControladorGestorReferenciasRemotasAdministradorImpl objRemoto2;

    public ControladorGestionarEntradaSalidaImpl(
            EntradasRepositoryInt objEntradasRepository,
            ControladorGestorUsuariosEntradaSalidaInt objRemotoServidorUsuarios,
            ControladorGestorReferenciasRemotasAdministradorImpl objRemoto2) throws RemoteException
    {
        super(); //se asigna un puerto de escucha al OR
        this.objEntradasRepository = objEntradasRepository;
        this.objRemotoServidorUsuarios = objRemotoServidorUsuarios;
        this.objRemoto2 = objRemoto2;
    } 

    @Override
    public int registrarEntrada(int identificacion) throws RemoteException {
        System.out.println("Verificando identificaci�n: " + identificacion + "...");
        int codigo = 0;
        EventoDTO objEventoDTO = null;
        UsuarioEntradaSalidaDTO objUsuarioDTO = objRemotoServidorUsuarios.consultarUsuarioEntradaSalida(identificacion);
        
        if (objUsuarioDTO == null) {
            codigo = 1;
            objEventoDTO = new EventoDTO("Entrada no exitosa, el usuario "+identificacion+" no existe", "Entrada");
        }
        else if (objRemotoServidorUsuarios.consultarUsuarioEntradaSalida(identificacion) != null) {
            if (objEntradasRepository.existeRegistradaIdentificacion(identificacion) == true) {
                //Si el usuario existe y esta adentro retorna 2
                codigo = 2;        
                objEventoDTO = new EventoDTO("Entrada no exitosa, el usuario "+identificacion+" esta dentro", "Entrada");
            } else {
                //Si el usuario existe y no esta adentro se retorna 3
                objEntradasRepository.registrarEntrada(identificacion);
                codigo = 3;
                objEventoDTO = new EventoDTO("Entrada exitosa del usuario "+identificacion, "Entrada");
            }            
        }
        this.objRemoto2.notificar(objEventoDTO, objUsuarioDTO);
        return codigo;
    }

    @Override
    public int registrarSalida(int identificacion) throws RemoteException {
        System.out.println("Eliminando identificaci�n: " + identificacion + "...");
        int codigo = 0;
        EventoDTO objEventoDTO = null;
        
        UsuarioEntradaSalidaDTO objUsuarioDTO = objRemotoServidorUsuarios.consultarUsuarioEntradaSalida(identificacion);
        
        if (objUsuarioDTO == null) {
            codigo = 1;
            objEventoDTO = new EventoDTO("Salida no exitosa, el usuario "+identificacion+" no existe", "Salida");
        }
        else if (objRemotoServidorUsuarios.consultarUsuarioEntradaSalida(identificacion) != null) {
            if (objEntradasRepository.existeRegistradaIdentificacion(identificacion) == false) {
                //Si el usuario existe y no esta adentro retorna 2
                codigo = 2;         
                objEventoDTO = new EventoDTO("Salida no exitosa, el usuario "+identificacion+" no esta adentro", "Salida");
            } else {
                //Si el usuario existe y esta adentro se retorna 3
                objEntradasRepository.eliminarEntrada(identificacion);
                objEventoDTO = new EventoDTO("Salida exitosa del usuario "+identificacion, "Salida");
                codigo = 3;
            }            
        }
        this.objRemoto2.notificar(objEventoDTO, objUsuarioDTO);
        return codigo;
    }    

    @Override
    public List<UsuarioEntradaSalidaDTO> consultarUsuariosAccesados() throws RemoteException {
        ArrayList<UsuarioEntradaSalidaDTO> lstUsuariosAccesados = new ArrayList();
        
        List<Integer> identificadores = this.objEntradasRepository.listarUsuariosAccesados();
        
        UsuarioEntradaSalidaDTO usuario;
        for (int i = 0; i < identificadores.size(); i++) {
            usuario = objRemotoServidorUsuarios.consultarUsuarioEntradaSalida(identificadores.get(i));
            lstUsuariosAccesados.add(usuario);
        }
        return lstUsuariosAccesados;
    }
    
}
