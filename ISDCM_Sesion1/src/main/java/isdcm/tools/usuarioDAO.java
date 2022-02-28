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
    int r;
    
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
    
}
