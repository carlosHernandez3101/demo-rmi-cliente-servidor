package servidor.Repositorios;

import servidor.DTO.LoginDTO;

public interface LoginRepositoryInt
{    
    public boolean iniciarSesion(LoginDTO objLogin);
}


