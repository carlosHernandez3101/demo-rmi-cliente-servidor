

package servidor.controladores;

import cliente.DTO.EventoDTO;
import cliente.callback.ControladorCallbackInt;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import servidor.DTO.UsuarioEntradaSalidaDTO;

public class ControladorGestorReferenciasRemotasAdministradorImpl extends UnicastRemoteObject implements ControladorGestorReferenciasRemotasAdministradorInt{
    
    private final LinkedList<ControladorCallbackInt>referencias;

    public ControladorGestorReferenciasRemotasAdministradorImpl() throws RemoteException{
        super();
        this.referencias = new LinkedList();
    }
    
    @Override
    public boolean registrarReferencia(ControladorCallbackInt referencia) throws RemoteException {
        System.out.println("\nProceso de registro de referencia iniciado...");
        return this.referencias.add(referencia);
    }
    
    public void notificar(EventoDTO objEvento, UsuarioEntradaSalidaDTO objUsuarioDTO){
        System.out.println("\nProceso de notificacion iniciado...");
        referencias.forEach(
        ref-> {
            try {
                ref.notificar(objEvento, objUsuarioDTO);
            } catch (RemoteException ex) {
                System.out.println("Administrador no existe");
            }
        }
        );
    }   
    
}
