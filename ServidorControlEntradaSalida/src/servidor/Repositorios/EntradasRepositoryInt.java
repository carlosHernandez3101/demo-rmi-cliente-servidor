
package servidor.Repositorios;

import java.util.List;
import servidor.DTO.UsuarioEntradaSalidaDTO;

public interface EntradasRepositoryInt
{    
    public boolean registrarEntrada(int identificacion);
    public boolean eliminarEntrada(int identificacion);
    public boolean existeRegistradaIdentificacion(int identificacion);
    public List<Integer> listarUsuariosAccesados();
}


