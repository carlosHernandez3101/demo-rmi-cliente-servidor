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
        System.out.println("Entrando a registrar...");
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
        System.out.println("Entrando a Cantidad");
        return this.misUsuariosEntradaSalida.size();
    }

    @Override
    public UsuarioEntradaSalidaDTO consultarUsuarioEntradaSalida(int identificacion)
    {
        UsuarioEntradaSalidaDTO objUsuarioES = null;
        System.out.println("Entrando a Consultar Entrada Salida...");
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
}
