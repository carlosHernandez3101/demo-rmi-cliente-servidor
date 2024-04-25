

package servidor.controladores;

import cliente.DTO.EventoDTO;
import cliente.callback.ControladorCallbackInt;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;

public class ControladorGestorReferenciasRemotasAdministradorImpl extends UnicastRemoteObject implements ControladorGestorReferenciasRemotasAdministradorInt{
    
    private final LinkedList<ControladorCallbackInt>referencias;

    public ControladorGestorReferenciasRemotasAdministradorImpl() throws RemoteException{
        super();
        this.referencias = new LinkedList();
    }
    
    @Override
    public boolean registrarReferencia(ControladorCallbackInt referencia) throws RemoteException {
        return this.referencias.add(referencia);
    }
    
    public void notificar(EventoDTO objEvento){
        referencias.forEach(
        ref-> {
            try {
                ref.notificar(objEvento);
            } catch (RemoteException ex) {
                System.out.println("Administrador no existe");
            }
        }
        );
    }   
    
}
