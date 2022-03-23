/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import isdcm_sesion2.modelo.video;

/**
 *
 * @author alumne
 */
public class videoDAO {
    static Connection con;
    static Conexion_DB cn = new Conexion_DB();
    static PreparedStatement ps;
    static ResultSet rs;
    static int r,ID, col;



    public static List<video> getVideos(String parametro, String value) {


        ArrayList<video> videos = new ArrayList<video>();
        try {
                      
            con = cn.getConnection();
            ps = con.prepareStatement("Select * from videos where " + parametro + " =?");
            ps.setString(1,value);
            rs = ps.executeQuery();
            while (rs.next()) {
                video video1 = new video();
                video1.setTitulo(rs.getString("TITULO"));
                video1.setAutor(rs.getString("AUTOR"));
                video1.setFecha_creacion(rs.getDate("FECHA_DE_CREACION"));
                video1.setDuracion(rs.getTime("DURACION"));
                video1.setReproducciones(rs.getInt("NUMERO_DE_REPRODUCCIONES"));
                video1.setDescripcion(rs.getString("DESCRIPCION"));
                video1.setFormato(rs.getString("FORMATO"));
                videos.add(video1);

            }
            
        } catch (SQLException e) {

            printSQLException(e);
        }
        return videos;
    }

    public static int getReproducciones(String enlace) {
        try {
            con = cn.getConnection();
            ps = con.prepareStatement("SELECT REPRODUCCIONES FROM VIDEOS WHERE ENLACE = ?");
            ps.setString(1,enlace);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("REPRODUCCIONES"); //Validar
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    public static boolean incrementReproducciones(String enlace) {
        try {
            con = cn.getConnection();
            ps = con.prepareStatement("UPDATE VIDEOS SET REPRODUCCIONES = REPRODUCCIONES + 1 WHERE ENLACE = ?");
            ps.setString(1,enlace);
            int col = ps.executeUpdate();

            if (col>0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static void printSQLException(SQLException ex) {
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
