package cliente.vista;

import cliente.utilidades.UtilidadesConsola;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;
import servidor.DTO.UsuarioEntradaSalidaDTO;
import servidor.controladores.ControladorGestorUsuariosEntradaSalidaInt;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidor.DTO.LoginDTO;
import servidor.DTO.UsuarioEntradaSalidaDTO.Rol;

public class Menu {

    private final ControladorGestorUsuariosEntradaSalidaInt objRemoto;
    private Scanner scanner;

    public Menu(ControladorGestorUsuariosEntradaSalidaInt objRemoto) {
        this.objRemoto = objRemoto;
        this.scanner = new Scanner(System.in);  // InicializaciÃ³n del objeto Scanner
    }

    public void validarCredenciales() {
        System.out.println("\n=============LOGIN DEL SISTEMA=============");
        boolean accesado = false;
        while (accesado == false) {
            try {
                System.out.println("\nIngrese las credenciales asignadas: ");
                System.out.println("Username: ");
                String username = cliente.utilidades.UtilidadesConsola.leerCadena();
                System.out.println("Password: ");
                String password = cliente.utilidades.UtilidadesConsola.leerCadena();

                LoginDTO objLoginDTO = new LoginDTO(username, password);
                if (objRemoto.iniciarSesion(objLoginDTO)) {
                    accesado = true;
                }
            } catch (RemoteException ex) {
                System.out.println("La operación no se pudo completar, intente nuevamente..."+ex.getMessage());
            }
        }
        
        ejecutarMenuPrincipal();
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
                    System.out.println("Salir...");
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }
        } while (opcion != 5);
    }

    private void Opcion1() {
        System.out.println("\n= = Registro de Usuario = =");
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
                    System.out.println("Rol inválido, por favor intente de nuevo.");
                }
            }
            // Capturar la fecha actual como fecha de registro
            LocalDate fechaRegistro = LocalDate.now();
            // CreaciÃ³n del DTO con la fecha de registro
            UsuarioEntradaSalidaDTO objUsuarioEntradaSalida = new UsuarioEntradaSalidaDTO(codigo, nombre, apellidos, rol, fechaRegistro);
            boolean bandera = objRemoto.registrarUsuarioEntradaSalida(objUsuarioEntradaSalida); // InvocaciÃ³n del mÃ©todo remoto
            if (bandera) {
                System.out.println("Registro realizado satisfactoriamente...");
            } else {
                System.out.println("No se pudo realizar el registro...");
            }
        } catch (RemoteException e) {
            System.out.println("La operación no se pudo completar, intente nuevamente...");
        }
    }

    private void Opcion2() {
        System.out.println("\n= = Listar Usuarios = =");
        try {
            List<UsuarioEntradaSalidaDTO> lstUsuarios = objRemoto.listarUsuariosEntradaSalida();
            if (lstUsuarios.isEmpty()) {
                System.out.println("No hay usuarios registrados.");
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
                System.out.println("Cantidad de Usuarios: " + lstUsuarios.size());
            }
        } catch (RemoteException e) {
            System.out.println("La operación no se pudo completar, intente nuevamente...");
            System.out.println("Excepción generada: " + e.getMessage());
        }
    }

    private void Opcion3() {
        System.out.println("\n= = Consultar Usuario = =");
        try {
            System.out.print("Ingrese el código del usuario a consultar: ");
            int codigo = UtilidadesConsola.leerEntero();
            UsuarioEntradaSalidaDTO objUsuarioEntradaSalida = objRemoto.consultarUsuarioEntradaSalida(codigo);

            if (objUsuarioEntradaSalida != null) {
                System.out.println("Código: " + objUsuarioEntradaSalida.getIdentificacion());
                System.out.println("Nombre: " + objUsuarioEntradaSalida.getNombre());
                System.out.println("Apellidos: " + objUsuarioEntradaSalida.getApellidos());
                System.out.println("Rol: " + objUsuarioEntradaSalida.getRol());
                System.out.println("Fecha de Registro: " + objUsuarioEntradaSalida.getFechaRegistro());
            } else {
                System.out.println("No se encontró ningún usuario con el código proporcionado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("El código ingresado no es válido. Por favor, ingrese un número.");
        } catch (RemoteException e) {
            System.out.println("La operación no se pudo completar, intente nuevamente...");
            System.out.println("Excepción generada: " + e.getMessage());
        }
    }

    private void Opcion4() {
        Scanner scanner = new Scanner(System.in); // Crea un objeto Scanner para leer la entrada del usuario
        System.out.println("\n= = Eliminación de Usuario = =");
        try {
            System.out.print("Ingrese el código del usuario a eliminar: ");
            int codigo = UtilidadesConsola.leerEntero();
            UsuarioEntradaSalidaDTO objUsuarioEntradaSalida = objRemoto.consultarUsuarioEntradaSalida(codigo);

            if (objUsuarioEntradaSalida != null) {
                String respuesta;
                do {
                    System.out.print("¿Desea eliminar al usuario con identificación " + codigo + "? (s/n): ");
                    respuesta = scanner.nextLine().trim().toLowerCase();
                    if (!respuesta.equals("s") && !respuesta.equals("n")) {
                        System.out.println("Respuesta inválida. Por favor, ingrese 's' para si o 'n' para no.");
                    }
                } while (!respuesta.equals("s") && !respuesta.equals("n"));

                if (respuesta.equals("s")) {
                    objRemoto.eliminarUsuarioEntradaSalida(codigo);
                    System.out.println("Usuario eliminado exitosamente.");
                } else {
                    System.out.println("Operaci?n cancelada.");
                }
            } else {
                System.out.println("No se encontr? un usuario con la identificaci?n " + codigo + ".");
            }
        } catch (NumberFormatException e) {
            System.out.println("El c?digo ingresado no es v?lido. Por favor, ingrese un n?mero.");
        } catch (RemoteException e) {
            System.out.println("La operaci?n no se pudo completar, intente nuevamente...");
            System.out.println("Excepci?n generada: " + e.getMessage());
        }
    }
}
