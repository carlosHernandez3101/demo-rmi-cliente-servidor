package servidor.Repositorios;

import java.util.LinkedList;

public class EntradasRepositoryImpl implements EntradasRepositoryInt {

    private final LinkedList<Integer> identificadores;

    public EntradasRepositoryImpl() {
        this.identificadores = new LinkedList();
    }

    @Override
    public boolean registrarEntrada(int identificacion) {
        System.out.println("Registrando Entrada...");
        return this.identificadores.add(identificacion);
    }

    @Override
    public boolean eliminarEntrada(int identificacion) {
        System.out.println("Eliminando Entrada...");
        boolean bandera = false;
        for (int i = 0; i < this.identificadores.size(); i++) {
            if (this.identificadores.get(i) == identificacion) {
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
            if (this.identificadores.get(i) == identificacion) {
                bandera = true;
                break;
            }
        }
        return bandera;
    }

}
