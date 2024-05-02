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
            System.out.println("= = Menú = =");
            System.out.println("1. Entrar a las instalaciones.");
            System.out.println("2. Salir de las instalaciones.");
            System.out.println("3. Salir");
            System.out.println("Digite opción:");
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
                    System.out.println("Opción incorrecta");
            }
        } while (opcion != 3);
    }

    private void Opcion1() {
        System.out.println("= = Entrada = =");
        try {
            int identificacion;
            System.out.println("Digite la identificación: ");
            identificacion = UtilidadesConsola.leerEntero();
            int resultado = objRemoto.registrarEntrada(identificacion); //InvocaciÃ³n mÃ©todo remoto
            switch (resultado) {
                case 1:
                    System.out.println("Error, el usuario no existe.");
                    break;
                case 2:
                    System.out.println("Error, el usuario existe y está dentro.");
                    break;
                case 3:
                    System.out.println("Entrada autorizada y registrada.");
                    break;
            }
        } catch (RemoteException e) {
            System.out.println("La operación no se pudo completar, intente nuevamente...");
        }
    }

    private void Opcion2() {
        System.out.println("= = Salida = =");
        try {
            int identificacion;
            System.out.println("Digite la identificación: ");
            identificacion = UtilidadesConsola.leerEntero();
            int resultado = objRemoto.registrarSalida(identificacion); //InvocaciÃ³n mÃ©todo remoto
             switch (resultado) {
                case 1:
                    System.out.println("Error, el usuario no existe.");
                    break;
                case 2:
                    System.out.println("Error, el usuario existe y no está dentro.");
                    break;
                case 3:
                    System.out.println("Salida autorizada y registrada.");
                    break;
            }
        } catch (RemoteException e) {
            System.out.println("La operación no se pudo completar, intente nuevamente...");
        }
    }
}
