package cliente.vista;

import cliente.utilidades.UtilidadesConsola;
import java.rmi.RemoteException;
import servidor.controladores.ControladorGestionarEntradaSalidaInt;

public class Menu {

    private final ControladorGestionarEntradaSalidaInt objRemoto;

    public Menu(ControladorGestionarEntradaSalidaInt objRemoto) {
        this.objRemoto = objRemoto;
    }

    public void ejecutarMenuPrincipal() {
        int opcion;
        do {
            System.out.println("= = Men� = =");
            System.out.println("1. Entrar a las instalaciones.");
            System.out.println("2. Salir de las instalaciones.");
            System.out.println("3. Salir");
            System.out.println("Digite opci�n:");
            opcion = UtilidadesConsola.leerEntero();
            switch (opcion) {
                case 1:
                    Opcion1();
                    break;
                case 2:
                    Opcion2();
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opci�n incorrecta");
            }
        } while (opcion != 3);
    }

    private void Opcion1() {
        System.out.println("= = Entrada = =");
        try {
            int identificacion;
            System.out.println("Digite la identificaci�n: ");
            identificacion = UtilidadesConsola.leerEntero();
            int resultado = objRemoto.registrarEntrada(identificacion); //Invocación método remoto
            switch (resultado) {
                case 1:
                    System.out.println("Error, el usuario no existe.");
                    break;
                case 2:
                    System.out.println("Error, el usuario existe y est� dentro.");
                    break;
                case 3:
                    System.out.println("Entrada autorizada y registrada.");
                    break;
            }
        } catch (RemoteException e) {
            System.out.println("La operaci�n no se pudo completar, intente nuevamente...");
        }
    }

    private void Opcion2() {
        System.out.println("= = Salida = =");
        try {
            int identificacion;
            System.out.println("Digite la identificaci�n: ");
            identificacion = UtilidadesConsola.leerEntero();
            int resultado = objRemoto.registrarSalida(identificacion); //Invocación método remoto
             switch (resultado) {
                case 1:
                    System.out.println("Error, el usuario no existe.");
                    break;
                case 2:
                    System.out.println("Error, el usuario existe y no est� dentro.");
                    break;
                case 3:
                    System.out.println("Salida autorizada y registrada.");
                    break;
            }
        } catch (RemoteException e) {
            System.out.println("La operaci�n no se pudo completar, intente nuevamente...");
        }
    }
}
