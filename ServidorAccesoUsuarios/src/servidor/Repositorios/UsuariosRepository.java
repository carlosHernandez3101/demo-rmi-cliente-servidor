package servidor.Repositorios;

import java.util.ArrayList;
import java.util.List;
import servidor.DTO.UsuarioEntradaSalidaDTO;


public class UsuariosRepository implements UsuariosRepositoryInt
{  
    private final ArrayList<UsuarioEntradaSalidaDTO> misUsuariosEntradaSalida;

    public UsuariosRepository()
    {        
        this.misUsuariosEntradaSalida = new ArrayList();
    }

    @Override
    public boolean registrarUsuarioEntradaSalida(UsuarioEntradaSalidaDTO objUsuarioES)
    {
        System.out.println("\nIniciando proceso de registro...");
        boolean bandera = false;
        
        if(this.misUsuariosEntradaSalida.size() < 5)
        {            
            bandera = this.misUsuariosEntradaSalida.add(objUsuarioES);
            if(bandera == true){
                System.out.println("Registrado con exito.");
            }
        }
        return bandera;
    }
    
    @Override
    public int consultarCantidadUsuariosEntradaSalida()
    {
        System.out.println("\nConsultando cantidad de usuarios...");
        return this.misUsuariosEntradaSalida.size();
    }

    @Override
    public UsuarioEntradaSalidaDTO consultarUsuarioEntradaSalida(int identificacion)
    {
        UsuarioEntradaSalidaDTO objUsuarioES = null;
        System.out.println("\nEntrando a Consultar Entrada Salida...");
        for (int i = 0; i < this.misUsuariosEntradaSalida.size(); i++) {
            if(this.misUsuariosEntradaSalida.get(i).getIdentificacion() == identificacion)
            {
                objUsuarioES = this.misUsuariosEntradaSalida.get(i);
                break;
            }
        }
        return objUsuarioES ;    
    }
    
    public List<UsuarioEntradaSalidaDTO> listarUsuariosEntradaSalida()
    {
        return this.misUsuariosEntradaSalida;
    }

    @Override
    public boolean eliminarUsuariosEntradaSalida(int identificacion) {
        System.out.println("\nEliminando registro...");
        // Recorre la lista de usuarios
        for (int i = 0; i < this.misUsuariosEntradaSalida.size(); i++) {
            // Comprueba si el usuario actual tiene la identificaci?n dada
            if (this.misUsuariosEntradaSalida.get(i).getIdentificacion() == identificacion) {
                // Elimina el usuario de la lista
                this.misUsuariosEntradaSalida.remove(i);
                // Retorna true indicando que el usuario fue eliminado exitosamente
                System.out.println("Eliminado con exito.");
                return true;
            }
        }
        System.out.println("Proceso de eliminacion cancelado.");
        return false;
    }
}
