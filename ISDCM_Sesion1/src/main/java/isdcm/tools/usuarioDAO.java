/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isdcm.tools;

import isdcm.model.usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Benny Hammer Pérez Vásquez
 */
public class usuarioDAO implements Validar{
    Connection con;
    Conexion_DB cn = new Conexion_DB();
    PreparedStatement ps;
    ResultSet rs;
    int r,ID;
    
    //Login de Usuario
    @Override
    public int validar(usuario usu){
        String sql="Select * from usuarios where NOMBRE_DE_USUARIO=? and CONTRASENHA=?";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1,usu.getNombre_de_usuario());
            ps.setString(2,usu.getContrasenha());
            rs=ps.executeQuery();
            
            while(rs.next()){
                r=r+1;
                usu.setNombre_de_usuario(rs.getString("NOMBRE_DE_USUARIO"));
                usu.setContrasenha(rs.getString("CONTRASENHA"));
            }    
            if(r==1){
                return 1;
            }else{
                return 0;
            }

                    
        }catch (Exception e){
            System.out.println(e.getStackTrace());
            return 0;
        } /*finally{
            try{
                if(con!= null)
                    con.close();
            } catch (Exception e){
                System.out.println(e.getStackTrace());
            }
        }*/
        
    }
    
    //Registro de usuario
    public int registerusu(usuario usu1) throws ClassNotFoundException {
        String sql = "INSERT INTO usuarios" +
            "  (ID, NOMBRE, APELLIDO, CORREO_ELECTRONICO, NOMBRE_DE_USUARIO, CONTRASENHA) VALUES " +
            " (?, ?, ?, ?, ?);";

        int result = 0;

        Class.forName("com.mysql.jdbc.Driver");

        try{
            
            con=cn.getConnection();
            ps=con.prepareStatement(sql);            
            ps.setInt(1, ID);
            ps.setString(2, usu1.getNombre());
            ps.setString(3, usu1.getApellido());
            ps.setString(4, usu1.getCorreo_electronico());
            ps.setString(5, usu1.getNombre_de_usuario());
            ps.setString(6, usu1.getContrasenha());
            //rs=ps.executeQuery();

            System.out.println(ps);
            // Step 3: Execute the query or update query
            result = ps.executeUpdate();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return result;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }


}
