/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isdcm.helpers;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Benny Hammer Pérez Vásquez
 */
public class Conexion_DB {

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
    
}
