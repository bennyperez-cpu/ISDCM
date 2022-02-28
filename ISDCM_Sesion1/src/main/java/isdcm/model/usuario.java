/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isdcm.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 *
 * @author Benny Hammer Pérez Vásquez
 */
public class usuario {
    private String nombre;
    private String apellido;
    private String correo_electronico;
    private String nombre_de_usuario;
    private String contrasenha;

    public usuario() {
        this.nombre = "";
        this.apellido = "";
        this.correo_electronico = "";
        this.nombre_de_usuario = "";
        this.contrasenha = "";
    }
    
    public usuario(String nombre, String apellido, String correo_electronico, 
                String nombre_de_usuario, String contrasenha) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo_electronico = correo_electronico;
        this.nombre_de_usuario = nombre_de_usuario; 
        this.contrasenha = contrasenha;
    }
    
 /*   public Usuario(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  */  
    public String getUsername() {
        return nombre_de_usuario;
    }

    public void setUsername(String username) {
        this.nombre_de_usuario = nombre_de_usuario;
    }

    public String getPassword() {
        return contrasenha;
    }

    public void setPassword(String password) {
        this.contrasenha = contrasenha;
    }

    public String getName() {
        return nombre;
    }

    public void setName(String name) {
        this.nombre = nombre;
    }

    public String getSurname() {
        return apellido;
    }

    public void setSurname(String surname) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return correo_electronico;
    }

    public void setEmail(String email) {
        this.correo_electronico = correo_electronico;
    }
    
/*
    public String queryTest (String user, String passwd){
        String result = "El usuario existe";
     
        Connection c = null;
        try {
            PreparedStatement statement;
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");

            // create a database connection
            c = DriverManager.getConnection("jdbc:derby://localhost:1527/pr21;user=pr21;password=pr21");
            
            String query = "select count(*) from usuarios where id_user=? and password=?";
            
            statement = c.prepareStatement(query);
            statement.setString(1, user);
            statement.setString(2, passwd);   
            
            ResultSet r = statement.executeQuery();
            
            if (r.next())
            {
                if (r.getInt(1) == 0)
                    result = "El usuario no existe";
            }
            
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        } finally {
            try {
                if (c != null) 
                    c.close();                
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }
        }
        
        return result;                        
    }


*/
   
}