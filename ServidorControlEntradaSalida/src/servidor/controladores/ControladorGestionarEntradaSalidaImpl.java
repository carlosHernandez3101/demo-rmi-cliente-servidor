package servidor.controladores;

import cliente.DTO.EventoDTO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import servidor.DTO.UsuarioEntradaSalidaDTO;
import servidor.DTO.UsuarioAccesadoDTO;
import servidor.Repositorios.EntradasRepositoryInt;

public class ControladorGestionarEntradaSalidaImpl extends UnicastRemoteObject implements ControladorGestionarEntradaSalidaInt {

    private final EntradasRepositoryInt objEntradasRepository;
    private final ControladorGestorUsuariosEntradaSalidaInt objRemotoServidorUsuarios;
    private final ControladorGestorReferenciasRemotasAdministradorImpl objRemoto2;

    public ControladorGestionarEntradaSalidaImpl(
            EntradasRepositoryInt objEntradasRepository,
            ControladorGestorUsuariosEntradaSalidaInt objRemotoServidorUsuarios,
            ControladorGestorReferenciasRemotasAdministradorImpl objRemoto2) throws RemoteException {
        super(); //se asigna un puerto de escucha al OR
        this.objEntradasRepository = objEntradasRepository;
        this.objRemotoServidorUsuarios = objRemotoServidorUsuarios;
        this.objRemoto2 = objRemoto2;
    }

    @Override
    public int registrarEntrada(int identificacion) throws RemoteException {
        System.out.println("\nIniciando proceso de verificacion de identificación: " + identificacion + "...");
        int codigo = 0;
        EventoDTO objEventoDTO = null;
        UsuarioEntradaSalidaDTO objUsuarioDTO = objRemotoServidorUsuarios.consultarUsuarioEntradaSalida(identificacion);
        UsuarioAccesadoDTO objUsuarioAccesadoDTO;
        if (objUsuarioDTO == null) {
            codigo = 1;
            objEventoDTO = new EventoDTO("Entrada no exitosa, el usuario " + identificacion + " no existe", "Entrada");
        } else if (objEntradasRepository.existeRegistradaIdentificacion(identificacion) == true) {
            //Si el usuario existe y esta adentro retorna 2
            codigo = 2;
            objEventoDTO = new EventoDTO("Entrada no exitosa, el usuario " + identificacion + " esta dentro", "Entrada");
        } else {
            //Si el usuario existe y no esta adentro se retorna 3
            objUsuarioAccesadoDTO = new UsuarioAccesadoDTO(identificacion);
            objEntradasRepository.registrarEntrada(objUsuarioAccesadoDTO);
            codigo = 3;
            objEventoDTO = new EventoDTO("Entrada exitosa del usuario " + identificacion, "Entrada");
        }

        this.objRemoto2.notificar(objEventoDTO, objUsuarioDTO);
        return codigo;
    }

    @Override
    public int registrarSalida(int identificacion) throws RemoteException {
        System.out.println("\nIniciando eliminacion de acceso de identificación: " + identificacion + "...");
        int codigo = 0;
        EventoDTO objEventoDTO = null;

        UsuarioEntradaSalidaDTO objUsuarioDTO = objRemotoServidorUsuarios.consultarUsuarioEntradaSalida(identificacion);

        if (objUsuarioDTO == null) {
            codigo = 1;
            objEventoDTO = new EventoDTO("Salida no exitosa, el usuario " + identificacion + " no existe", "Salida");
        } else if (objEntradasRepository.existeRegistradaIdentificacion(identificacion) == false) {
            //Si el usuario existe y no esta adentro retorna 2
            codigo = 2;
            objEventoDTO = new EventoDTO("Salida no exitosa, el usuario " + identificacion + " no esta adentro", "Salida");
        } else {
            //Si el usuario existe y esta adentro se retorna 3
            objEntradasRepository.eliminarEntrada(identificacion);
            objEventoDTO = new EventoDTO("Salida exitosa del usuario " + identificacion, "Salida");
            codigo = 3;
        }

        this.objRemoto2.notificar(objEventoDTO, objUsuarioDTO);
        return codigo;
    }

    @Override
    public List<UsuarioAccesadoDTO> consultarUsuariosAccesados() throws RemoteException {
        System.out.println("\nIniciando proceso de obtencion de listado de usuarios accesados...");

        List<UsuarioAccesadoDTO> identificadores = this.objEntradasRepository.listarUsuariosAccesados();

        return identificadores;
    }

    @Override
    public String generarTicket(int identificacion, String accion) throws RemoteException {
        UsuarioEntradaSalidaDTO objUsuarioDTO = this.objRemotoServidorUsuarios.consultarUsuarioEntradaSalida(identificacion);
        List<UsuarioAccesadoDTO> objLstUsuarioAccesadoDTO = this.objEntradasRepository.listarUsuariosAccesados();
        
        UsuarioAccesadoDTO usuarioAccesadoDTO = null;
        for (int i = 0; i < objLstUsuarioAccesadoDTO.size(); i++) {
            if (objLstUsuarioAccesadoDTO.get(i).getIdentificacion() == identificacion) {
                usuarioAccesadoDTO = objLstUsuarioAccesadoDTO.get(i);
                break;
            }
        }
        String datos = objUsuarioDTO.getRol() + "\n"
                + "" + objUsuarioDTO.getNombre() + " " + objUsuarioDTO.getApellidos() + "\n";
        String concat;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm: a");
        DateTimeFormatter formatterFecha = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
        DateTimeFormatter formatterS = DateTimeFormatter.ofPattern("h:mm a  dd 'de' MMMM 'de' yyyy");
        if(accion.equals("e")){
            concat = datos.concat("Hora y fecha de acceso: "+usuarioAccesadoDTO.getHoraEntrada().format(formatter)+" "+usuarioAccesadoDTO.getFechaEntrada().format(formatterFecha));
        }else{
            LocalDateTime fechaDeSalida = LocalDateTime.now();
            concat = datos.concat("Hora y fecha de salida: "+fechaDeSalida.format(formatterS));
        }
        
        return concat;
    }

}
