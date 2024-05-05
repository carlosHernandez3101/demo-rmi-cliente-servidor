package servidor.Repositorios;

import java.util.LinkedList;
import java.util.List;
import servidor.DTO.UsuarioAccesadoDTO;

public class EntradasRepositoryImpl implements EntradasRepositoryInt {

    private final LinkedList<UsuarioAccesadoDTO> identificadores;

    public EntradasRepositoryImpl() {
        this.identificadores = new LinkedList();
    }

    @Override
    public boolean registrarEntrada(UsuarioAccesadoDTO objUsuarioAccesadoDTO) {
        return this.identificadores.add(objUsuarioAccesadoDTO);
    }

    @Override
    public boolean eliminarEntrada(int identificacion) {
        boolean bandera = false;
        for (int i = 0; i < this.identificadores.size(); i++) {
            if (this.identificadores.get(i).getIdentificacion() == identificacion) {
                this.identificadores.remove(i);
                bandera = true;
                break;
            }
        }
        return bandera;
    }

    @Override
    public boolean existeRegistradaIdentificacion(int identificacion) {
        boolean bandera = false;
        for (int i = 0; i < this.identificadores.size(); i++) {
            if (this.identificadores.get(i).getIdentificacion() == identificacion) {
                bandera = true;
                break;
            }
        }
        return bandera;
    }

    @Override
    public List<UsuarioAccesadoDTO> listarUsuariosAccesados() {
        return this.identificadores;
    }

}
