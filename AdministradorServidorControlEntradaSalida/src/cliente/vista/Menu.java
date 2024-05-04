package cliente.vista;

import cliente.utilidades.UtilidadesConsola;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import servidor.DTO.UsuarioEntradaSalidaDTO;
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
            System.out.println("1. Consultar usuarios accesados");
            System.out.println("2. Salir");
            System.out.println("Digite opción:");
            opcion = UtilidadesConsola.leerEntero();
            switch (opcion) {
                case 1:
                    Opcion1();
                    break;
                case 2:
                    System.out.println("\nSaliendo...");
                    break;
                default:
                    System.out.println("\nOpción incorrecta");
            }
        } while (opcion != 2);
    }

    private void Opcion1() {
        System.out.println("\n= = Listado de usuarios accesados = =");
        try {

            List<UsuarioEntradaSalidaDTO> lstUsuarios = this.objRemoto.consultarUsuariosAccesados();
            if (lstUsuarios != null) {
                for (UsuarioEntradaSalidaDTO objUsuarioDTO : lstUsuarios) {
                    System.out.printf("\n\n%-10s  %-15s  %-10s \n", "ID", "Nombres", "Apellidos");
                    System.out.printf("%-10s  %-15s  %-10s \n", objUsuarioDTO.getIdentificacion(), objUsuarioDTO.getNombre(), objUsuarioDTO.getApellidos());
                }
            }
        } catch (RemoteException e) {
            System.out.println("\nLa operación no se pudo completar, intente nuevamente...");
        }
    }

    private void Opcion2() {
        System.out.println("\n= = Salida = =");
        try {
            int identificacion;
            System.out.println("Digite la identificación: ");
            identificacion = UtilidadesConsola.leerEntero();
            int resultado = objRemoto.registrarSalida(identificacion); //InvocaciÃ³n mÃ©todo remoto
            switch (resultado) {
                case 1:
                    System.out.println("\nError, el usuario no existe.");
                    break;
                case 2:
                    System.out.println("\nError, el usuario existe y no está dentro.");
                    break;
                case 3:
                    System.out.println("\nSalida autorizada y registrada.");
                    break;
            }
        } catch (RemoteException e) {
            System.out.println("\nLa operación no se pudo completar, intente nuevamente...");
        }
    }
}
