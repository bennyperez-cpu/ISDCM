package isdcm.helpers.dao;

import java.sql.*;

import isdcm.helpers.dto.VideoDTO;
import isdcm.helpers.jdbc.DataSource;

public class VideoDAO {
    
    
    public void insertVideo(VideoDTO video){
        try {
            DataSource ds = DataSource.getInstance();
            //here mydb is database name, mydb is username and password
//            Statement stmt = ds.getConnection().createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM MYDB.VIDEO");
//            if(rs.next()) {
//                
//            } 
            String insertNewUserSQL = "INSERT INTO MYDB.VIDEO (TITULO, AUTOR, FECHA_CREACION, DURACION, REPRODUCCIONES, DESCRIPCION, FORMATO) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = ds.getConnection().prepareStatement(insertNewUserSQL);
//            pstmt.setInt(1, rs.getInt(1));
            pstmt.setString(1, video.getTitulo());
            pstmt.setString(2, video.getAutor());
            //Modificar-ho més endavant
            java.sql.Date date1 = new java.sql.Date(video.getFechaCreacion().toEpochDay());
            java.sql.Time time1 = new java.sql.Time(video.getDuracion().toNanoOfDay());
            pstmt.setDate(3, date1);
            pstmt.setTime(4, time1);
            pstmt.setInt(5, video.getReproducciones());
            pstmt.setString(6, video.getDescripcion());
            pstmt.setString(7, video.getFormato());
            pstmt.executeUpdate();
            ds.getConnection().close();  
        }
        catch(SQLException e){ System.out.println(e);}
    }
}
