
package servidor.Repositorios;

import java.util.List;
import servidor.DTO.UsuarioAccesadoDTO;

public interface EntradasRepositoryInt
{    
    public boolean registrarEntrada(UsuarioAccesadoDTO objUsuarioAccesadoDTO);
    public boolean eliminarEntrada(int identificacion);
    public boolean existeRegistradaIdentificacion(int identificacion);
    public List<UsuarioAccesadoDTO> listarUsuariosAccesados();
}


