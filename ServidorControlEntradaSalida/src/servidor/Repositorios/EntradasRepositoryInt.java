
package servidor.Repositorios;

public interface EntradasRepositoryInt
{    
    public boolean registrarEntrada(int identificacion);
    public boolean eliminarEntrada(int identificacion);
    public boolean existeRegistradaIdentificacion(int identificacion);
}


