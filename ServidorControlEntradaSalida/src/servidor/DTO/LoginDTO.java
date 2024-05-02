/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.DTO;

/**
 *
 * @author ASUS
 */
public class LoginDTO {
    
    private String username;
    private String contraseña;

    public LoginDTO(String username, String contraseña) {
        this.username = username;
        this.contraseña = contraseña;
    }

    public String getUsername() {
        return username;
    }

    public String getContraseña() {
        return contraseña;
    }
    
}
