package cliente.servicios;

import cliente.callback.ControladorCallbackImpl;
import cliente.utilidades.UtilidadesRegistroC;
import cliente.vista.Menu;
import java.rmi.RemoteException;
import servidor.DTO.LoginDTO;
import servidor.controladores.ControladorGestorReferenciasRemotasAdministradorInt;
import servidor.controladores.ControladorGestorUsuariosEntradaSalidaInt;

public class ClienteDeObjetos {

    private static ControladorGestorUsuariosEntradaSalidaInt objRemoto1;
    private static ControladorGestorReferenciasRemotasAdministradorInt objRemoto2;

    public static void main(String[] args) {
        try {
            int numPuertoRMIRegistry1 = 0, numPuertoRMIRegistry2 = 0;
            String direccionIpRMIRegistry = "";

            System.out.println("Cual es el la dirección ip donde se encuentra el rmiregistry ");
            direccionIpRMIRegistry = cliente.utilidades.UtilidadesConsola.leerCadena();
            System.out.println("Cual es el número de puerto por el cual escucha el rmiregistry del servidor de usuarios");
            numPuertoRMIRegistry1 = cliente.utilidades.UtilidadesConsola.leerEntero();
            System.out.println("Cual es el número de puerto por el cual escucha el rmiregistry del servidor de entrada y salida");
            numPuertoRMIRegistry2 = cliente.utilidades.UtilidadesConsola.leerEntero();

            objRemoto1 = (ControladorGestorUsuariosEntradaSalidaInt) UtilidadesRegistroC.obtenerObjRemoto(
                    direccionIpRMIRegistry,
                    numPuertoRMIRegistry1,
                    "objServicioGestionUsuariosEntradaSalida");
            objRemoto2 = (ControladorGestorReferenciasRemotasAdministradorInt) UtilidadesRegistroC.obtenerObjRemoto(
                    direccionIpRMIRegistry,
                    numPuertoRMIRegistry2,
                    "objServicioGestionReferencia");

            ControladorCallbackImpl objRemoteCallBack = new ControladorCallbackImpl();
            objRemoto2.registrarReferencia(objRemoteCallBack);
            Menu objMenu = new Menu(objRemoto1);
            objMenu.validarCredenciales();
        } catch (RemoteException e) {
        }
    }
}
