/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.DTO;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class LoginDTO implements Serializable{
    
    private String username;
    private String contrase�a;

    public LoginDTO(String username, String contrase�a) {
        this.username = username;
        this.contrase�a = contrase�a;
    }

    public String getUsername() {
        return username;
    }

    public String getContrase�a() {
        return contrase�a;
    }
    
}
