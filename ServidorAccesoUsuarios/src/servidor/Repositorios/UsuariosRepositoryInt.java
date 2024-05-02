
package servidor.Repositorios;

import java.util.List;
import servidor.DTO.UsuarioEntradaSalidaDTO;

public interface UsuariosRepositoryInt
{    
    public boolean registrarUsuarioEntradaSalida(UsuarioEntradaSalidaDTO objUsuarioES);
    public int consultarCantidadUsuariosEntradaSalida();
    public UsuarioEntradaSalidaDTO consultarUsuarioEntradaSalida(int identificacion);
    public List<UsuarioEntradaSalidaDTO> listarUsuariosEntradaSalida();
    public boolean eliminarUsuariosEntradaSalida(int identificacion);
}


