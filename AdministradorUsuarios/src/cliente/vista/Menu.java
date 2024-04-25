package cliente.vista;

import cliente.utilidades.UtilidadesConsola;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;
import servidor.DTO.UsuarioEntradaSalidaDTO;
import servidor.controladores.ControladorGestorUsuariosEntradaSalidaInt;
import java.util.Scanner;
import servidor.DTO.UsuarioEntradaSalidaDTO.Rol;

public class Menu {

    private final ControladorGestorUsuariosEntradaSalidaInt objRemoto;
    private Scanner scanner;

    public Menu(ControladorGestorUsuariosEntradaSalidaInt objRemoto) {
        this.objRemoto = objRemoto;
        this.scanner = new Scanner(System.in);  // Inicialización del objeto Scanner
    }

    public void ejecutarMenuPrincipal() {
        int opcion = 0;
        do {
            System.out.println("= = Men� = =");
            System.out.println("1. Registrar Usuario que ingresa.");
            System.out.println("2. Listar Usuarios que ingresan.");
            System.out.println("3. Consultar Usuario.");
            System.out.println("4. Salir");
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
                    Opcion3();
                    break;
                case 4:
                    System.out.println("Salir...");
                    break;
                default:
                    System.out.println("Opci�n incorrecta");
            }
        } while (opcion != 4);
    }

    private void Opcion1() {
        System.out.println("= = Registro de Usuario = =");
        try {
            System.out.println("Ingrese el codigo: ");
            int codigo = UtilidadesConsola.leerEntero();
            System.out.println("Ingrese el nombre: ");
            String nombre = UtilidadesConsola.leerCadena();
            System.out.println("Ingrese los apellidos: ");
            String apellidos = UtilidadesConsola.leerCadena();
            System.out.println("Ingrese el rol: ");
            Rol rol = null;
            while (rol == null) {
                System.out.println("Ingrese el rol (ADMINISTRATIVO, DOCENTE, ESTUDIANTE):");
                String rolInput = scanner.nextLine().toUpperCase().trim();
                try {
                    rol = Rol.valueOf(rolInput);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Rol inv�lido, por favor intente de nuevo.");
                }
            }
            // Capturar la fecha actual como fecha de registro
            LocalDate fechaRegistro = LocalDate.now();
            // Creación del DTO con la fecha de registro
            UsuarioEntradaSalidaDTO objUsuarioEntradaSalida = new UsuarioEntradaSalidaDTO(codigo, nombre, apellidos, rol, fechaRegistro);
            boolean bandera = objRemoto.registrarUsuarioEntradaSalida(objUsuarioEntradaSalida); // Invocación del método remoto
            if (bandera) {
                System.out.println("Registro realizado satisfactoriamente...");
            } else {
                System.out.println("No se pudo realizar el registro...");
            }
        } catch (RemoteException e) {
            System.out.println("La operaci�n no se pudo completar, intente nuevamente...");
        }
    }

   private void Opcion2() {
    System.out.println("= = Listar Usuarios = =");
    try {
        List<UsuarioEntradaSalidaDTO> lstUsuarios = objRemoto.listarUsuariosEntradaSalida();
        if (lstUsuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
        } else {
            System.out.println("------------------------------");
            for (UsuarioEntradaSalidaDTO objUsuarioEntradaSalida : lstUsuarios) {
                System.out.println("C�digo: " + objUsuarioEntradaSalida.getIdentificacion());
                System.out.println("Nombre: " + objUsuarioEntradaSalida.getNombre());
                System.out.println("Apellidos: " + objUsuarioEntradaSalida.getApellidos());
                System.out.println("Rol: " + objUsuarioEntradaSalida.getRol());
                System.out.println("Fecha de Registro: " + objUsuarioEntradaSalida.getFechaRegistro());
                System.out.println("------------------------------");
            }
            System.out.println("Cantidad de Usuarios: " + lstUsuarios.size());
        }
    } catch (RemoteException e) {
        System.out.println("La operaci�n no se pudo completar, intente nuevamente...");
        System.out.println("Excepci�n generada: " + e.getMessage());
    }
}


    private void Opcion3() {
        System.out.println("= = Consultar Usuario = =");
        try {
            System.out.print("Ingrese el c�digo del usuario a consultar: ");
            int codigo = UtilidadesConsola.leerEntero();
            UsuarioEntradaSalidaDTO objUsuarioEntradaSalida = objRemoto.consultarUsuarioEntradaSalida(codigo); 
            
            if (objUsuarioEntradaSalida != null) {
                System.out.println("C�digo: " + objUsuarioEntradaSalida.getIdentificacion());
                System.out.println("Nombre: " + objUsuarioEntradaSalida.getNombre());
                System.out.println("Apellidos: " + objUsuarioEntradaSalida.getApellidos());
                System.out.println("Rol: " + objUsuarioEntradaSalida.getRol());
                System.out.println("Fecha de Registro: " + objUsuarioEntradaSalida.getFechaRegistro());
            } else {
                System.out.println("No se encontr� ning�n usuario con el c�digo proporcionado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("El c�digo ingresado no es v�lido. Por favor, ingrese un n�mero.");
        }
        catch (RemoteException e) {
            System.out.println("La operaci�n no se pudo completar, intente nuevamente...");
            System.out.println("Excepci�n generada: " + e.getMessage());
        }
    }
}
