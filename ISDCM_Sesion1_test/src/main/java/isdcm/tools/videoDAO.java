package isdcm.tools;

import isdcm.model.video;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class videoDAO {


    
    public static boolean registrovid(video video_nuevo) {
        Connection con;
        Conexion_DB cn = new Conexion_DB();
        PreparedStatement ps;
        ResultSet rs;
        int r,ID;

        String sql="INSERT INTO VIDEOS VALUES (?,?,?,?,?,?,?,?)";
        int result = 0;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, video_nuevo.getTitulo());
            ps.setString(2, video_nuevo.getAutor());
            ps.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
            ps.setTime(4, video_nuevo.getDuracion());
            ps.setInt(5, 0);
            ps.setString(6, video_nuevo.getDescripcion());
            ps.setString(7, video_nuevo.getFormato());
            //rs=ps.executeQuery();
            System.out.println(ps);
            result = ps.executeUpdate();
            

        } catch (SQLException e) {
            printSQLException(e);
        }
        return true;
    }

    public static List<video> getVideos() {

        Connection con;
        Conexion_DB cn = new Conexion_DB();
        PreparedStatement ps;
        ResultSet rs;
        int r,ID;

        ArrayList<video> videos = new ArrayList<video>();
        try {
            con = cn.getConnection();
            ps = con.prepareStatement("SELECT * FROM VIDEOS");
            rs = ps.executeQuery();
            while (rs.next()) {
                video video1 = new video();
                video1.setTitulo(rs.getString("TITULO"));
                video1.setAutor(rs.getString("AUTOR"));
                video1.setFecha_creacion(rs.getDate("FECHA_DE_CREACION"));
                video1.setDuracion(rs.getTime("DURACION"));
                video1.setDescripcion(rs.getString("DESCRIPCION"));
                video1.setFormato(rs.getString("FORMATO"));
                videos.add(video1);

            }
            
        } catch (SQLException e) {

            printSQLException(e);
        }
        return videos;
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
