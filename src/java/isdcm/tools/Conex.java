/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isdcm.tools;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author alumne
 */
public class Conex {
    private String driver="org.apache.derby.jdbc.ClientDriver";
    private String url="jdbc:derby://localhost:1527/ISDCM";
    private String usuario = "ISDCM";
    private String password = "ISDCM";

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}
