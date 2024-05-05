package servidor.Repositorios;

import java.util.ArrayList;
import servidor.DTO.LoginDTO;


public class LoginRepository implements LoginRepositoryInt
{  
    private final ArrayList<LoginDTO> lstLogin;

    public LoginRepository()
    {        
        this.lstLogin = new ArrayList();
        this.lstLogin.add(new LoginDTO("admin123","12345678"));
    }

    @Override
    public boolean iniciarSesion(LoginDTO objLogin) {
        boolean accesado = false;
        System.out.println("Validando credenciales..." );
        for (int i = 0; i < lstLogin.size(); i++) {
            if(this.lstLogin.get(i).getUsername().equals(objLogin.getUsername()) && this.lstLogin.get(i).getContrase�a().equals(objLogin.getContrase�a())){                
                accesado = true;
            }
        }        
        return accesado;
    }
}
