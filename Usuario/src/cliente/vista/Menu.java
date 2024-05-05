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
            System.out.println("\n= = Menú = =");
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
                    System.out.println("\nSaliendo...");
                    break;
                default:
                    System.out.println("\nOpción incorrecta");
            }
        } while (opcion != 3);
    }
    
    private int leerIdentificacion(String msj) {
        boolean idValido = false;
        int identificacion;
        String id;
        do {
            System.out.println(msj);
            identificacion = UtilidadesConsola.leerEntero();
            id = String.valueOf(identificacion);
            idValido = (id.length() == 8);
            if (idValido == false) {
                System.out.println("\nInvalido. El codigo debe ser de 8 caracteres. Intente nuevamente.\n");
            }
        } while (idValido == false);

        return identificacion;
    }

    private void Opcion1() {
        System.out.println("\n= = Entrada = =");
        try {
            int identificacion;
            identificacion = leerIdentificacion("Digite la identificación: ");
            int resultado = objRemoto.registrarEntrada(identificacion); //InvocaciÃ³n mÃ©todo remoto
            switch (resultado) {
                case 1:
                    System.out.println("\nError, el usuario no existe.");
                    break;
                case 2:
                    System.out.println("\nError, el usuario existe y está dentro.");
                    break;
                case 3:
                    System.out.println("\n    Acceso concedido.\n");
                    System.out.println(objRemoto.generarTicket(identificacion, "e"));
                    break;
            }
        } catch (RemoteException e) {
            System.out.println("\nLa operación no se pudo completar, intente nuevamente...");
        }
    }

    private void Opcion2() {
        System.out.println("\n= = Salida = =");
        try {
            int identificacion;
            identificacion = leerIdentificacion("Digite la identificación: ");
            int resultado = objRemoto.registrarSalida(identificacion); //InvocaciÃ³n mÃ©todo remoto
             switch (resultado) {
                case 1:
                    System.out.println("\nError, el usuario no existe.");
                    break;
                case 2:
                    System.out.println("\nError, el usuario existe y no está dentro.");
                    break;
                case 3:
                    System.out.println("\n    Salida concedida.\n");
                    System.out.println(objRemoto.generarTicket(identificacion, "s"));
                    break;
            }
        } catch (RemoteException e) {
            System.out.println("\nLa operación no se pudo completar, intente nuevamente...");
        }
    }
}
