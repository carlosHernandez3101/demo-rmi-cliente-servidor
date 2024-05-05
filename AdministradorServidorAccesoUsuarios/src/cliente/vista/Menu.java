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
        this.scanner = new Scanner(System.in);  // InicializaciÃ³n del objeto Scanner
    }

    public void ejecutarMenuPrincipal() {
        int opcion = 0;
        do {
            System.out.println("\n= = Menú = =");
            System.out.println("1. Registrar Usuario que ingresa.");
            System.out.println("2. Listar Usuarios que ingresan.");
            System.out.println("3. Consultar Usuario.");
            System.out.println("4. Eliminar Usuario.");
            System.out.println("5. Salir");
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
                    Opcion3();
                    break;
                case 4:
                    Opcion4();
                    break;
                case 5:
                    System.out.println("\nSaliendo...");
                    break;
                default:
                    System.out.println("\nOpción incorrecta");
            }
        } while (opcion != 5);
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
        UsuarioEntradaSalidaDTO usuarioID;
        boolean existeUsuarioConID;
        int codigo;
        System.out.println("\n= = Registro de Usuario = =");
        try {
            do {
                codigo = leerIdentificacion("Ingrese el codigo: ");
                usuarioID = objRemoto.consultarUsuarioEntradaSalida(codigo);
                if (usuarioID != null) {
                    System.out.println("\nInvalido. El codigo ya se encuentra registrado. Intente nuevamente.\n");
                    existeUsuarioConID = true;
                } else {
                    existeUsuarioConID = false;
                }
            } while (existeUsuarioConID == true);

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
                    System.out.println("Rol inválido, por favor intente de nuevo.");
                }
            }
            // Capturar la fecha actual como fecha de registro
            LocalDate fechaRegistro = LocalDate.now();
            // CreaciÃ³n del DTO con la fecha de registro
            UsuarioEntradaSalidaDTO objUsuarioEntradaSalida = new UsuarioEntradaSalidaDTO(codigo, nombre, apellidos, rol, fechaRegistro);
            boolean bandera = objRemoto.registrarUsuarioEntradaSalida(objUsuarioEntradaSalida); // InvocaciÃ³n del mÃ©todo remoto
            if (bandera) {
                System.out.println("\nRegistro realizado satisfactoriamente...");
            } else {
                System.out.println("\nNo se pudo realizar el registro...");
            }
        } catch (RemoteException e) {
            System.out.println("\nLa operación no se pudo completar, intente nuevamente...");
        }
    }

    private void Opcion2() {
        System.out.println("\n= = Listar Usuarios = =");
        try {
            List<UsuarioEntradaSalidaDTO> lstUsuarios = objRemoto.listarUsuariosEntradaSalida();
            if (lstUsuarios.isEmpty()) {
                System.out.println("\nNo hay usuarios registrados.");
            } else {
                System.out.println("------------------------------");
                for (UsuarioEntradaSalidaDTO objUsuarioEntradaSalida : lstUsuarios) {
                    System.out.println("Código: " + objUsuarioEntradaSalida.getIdentificacion());
                    System.out.println("Nombre: " + objUsuarioEntradaSalida.getNombre());
                    System.out.println("Apellidos: " + objUsuarioEntradaSalida.getApellidos());
                    System.out.println("Rol: " + objUsuarioEntradaSalida.getRol());
                    System.out.println("Fecha de Registro: " + objUsuarioEntradaSalida.getFechaRegistro());
                    System.out.println("------------------------------");
                }
                System.out.println("\nCantidad de Usuarios: " + lstUsuarios.size());
            }
        } catch (RemoteException e) {
            System.out.println("\nLa operación no se pudo completar, intente nuevamente...");
            System.out.println("Excepción generada: " + e.getMessage());
        }
    }

    private void Opcion3() {
        System.out.println("\n= = Consultar Usuario = =");
        try {
            int codigo = leerIdentificacion("Ingrese el código del usuario a consultar: ");
            UsuarioEntradaSalidaDTO objUsuarioEntradaSalida = objRemoto.consultarUsuarioEntradaSalida(codigo);

            if (objUsuarioEntradaSalida != null) {
                System.out.println("\nCódigo: " + objUsuarioEntradaSalida.getIdentificacion());
                System.out.println("Nombre: " + objUsuarioEntradaSalida.getNombre());
                System.out.println("Apellidos: " + objUsuarioEntradaSalida.getApellidos());
                System.out.println("Rol: " + objUsuarioEntradaSalida.getRol());
                System.out.println("Fecha de Registro: " + objUsuarioEntradaSalida.getFechaRegistro());
            } else {
                System.out.println("\nNo se encontró ningún usuario con el código proporcionado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("\nEl código ingresado no es válido. Por favor, ingrese un número.");
        } catch (RemoteException e) {
            System.out.println("\nLa operación no se pudo completar, intente nuevamente...");
            System.out.println("Excepción generada: " + e.getMessage());
        }
    }

    private void Opcion4() {
        Scanner scanner = new Scanner(System.in); // Crea un objeto Scanner para leer la entrada del usuario
        System.out.println("\n= = Eliminación de Usuario = =");
        try {           
            int codigo = leerIdentificacion("Ingrese el código del usuario a eliminar: ");
            UsuarioEntradaSalidaDTO objUsuarioEntradaSalida = objRemoto.consultarUsuarioEntradaSalida(codigo);

            if (objUsuarioEntradaSalida != null) {
                String respuesta;
                do {
                    System.out.print("\n¿Desea eliminar al usuario con identificación " + codigo + "? (s/n): ");
                    respuesta = scanner.nextLine().trim().toLowerCase();
                    if (!respuesta.equals("s") && !respuesta.equals("n")) {
                        System.out.println("\nRespuesta inválida. Por favor, ingrese 's' para si o 'n' para no.");
                    }
                } while (!respuesta.equals("s") && !respuesta.equals("n"));

                if (respuesta.equals("s")) {
                    objRemoto.eliminarUsuarioEntradaSalida(codigo);
                    System.out.println("\nUsuario eliminado exitosamente.");
                } else {
                    System.out.println("\nOperaci?n cancelada.");
                }
            } else {
                System.out.println("\nNo se encontr? un usuario con la identificaci?n " + codigo + ".");
            }
        } catch (NumberFormatException e) {
            System.out.println("\nEl c?digo ingresado no es v?lido. Por favor, ingrese un n?mero.");
        } catch (RemoteException e) {
            System.out.println("\nLa operaci?n no se pudo completar, intente nuevamente...");
            System.out.println("Excepci?n generada: " + e.getMessage());
        }
    }
}
