/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isdcm.tools;

import java.sql.*;
import java.util.*;

/**
 *
 * @author alumne
 */
public class DAOUsuario {
    private PreparedStatement ps;
    private ResultSet rs;
    Conex c = new Conex();
    Connection con() throws SQLException, ClassNotFoundException {
        Class.forName(c.getDriver());
        return DriverManager.getConnection(c.getUrl(),c.getUsuario(),c.getPassword());
    }
    public boolean validar(String login, String password){
        try{
            ps = con().prepareStatement("select * from USUARIOS where NOMBRE_DE_USUARIO=? and CONTRASENHA=?");
            ps.setString(1, login);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while(rs.next())
                return true;
        } catch (Exception e){
        }
        return false;
    }
}
