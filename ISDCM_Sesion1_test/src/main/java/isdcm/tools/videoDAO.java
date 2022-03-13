package isdcm.tools;

import isdcm.model.video;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class videoDAO {
        static Connection con;
        static Conexion_DB cn = new Conexion_DB();
        static PreparedStatement ps;
        static ResultSet rs;
        static int r,ID;

    
    public int registrovid(video video_nuevo) throws ClassNotFoundException {
        

        String sql="INSERT INTO VIDEOS" + "  (TITULO, AUTOR, FECHA_DE_CREACION, DURACION, NUMERO_DE_REPRODUCCIONES, DESCRIPCION, FORMATO) VALUES" +" (?, ?, ?, ?, ?, ?, ?)";
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
            return 0;
        }
        return 1;
    }


public int validar_vid(video vid){
        r=0;

 	String sql="Select * from videos where TITULO=?";
       // String sql = "insert into ISDCM.USUARIOS (NOMBRE,APELLIDO,CORREO_ELECTRONICO,NOMBRE_DE_USUARIO,CONTRASENHA) values ('sQW','sf','wf','wf','wef');";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1,vid.getTitulo());
            rs=ps.executeQuery();
            
            while(rs.next()){
                r=r+1;
                vid.setTitulo(rs.getString("TITULO"));
                
            }    
            if(r==0){
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
    
    public static List<video> getVideos(String parametro, String value) {


        ArrayList<video> videos = new ArrayList<video>();
        try {
            
  /*      Connection con;
        Conexion_DB cn = new Conexion_DB();
        PreparedStatement ps;
        ResultSet rs;
        int r,ID;*/
            
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
