/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isdcm.controller;

import isdcm.model.video;
import isdcm.tools.usuarioDAO;
import isdcm.tools.videoDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alumne
 */
@WebServlet(name = "servletRegistroVid", urlPatterns = {"/servletRegistroVid"})
public class servletRegistroVid extends HttpServlet {

    videoDAO videoDAO = new videoDAO();
    video video1 = new video();
    videoDAO videoDAO_reg = new videoDAO();
    video video_reg = new video();
    int r=0;    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                response.setContentType("text/html;charset=UTF-8");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println(request);
        String action = request.getParameter("action");

        switch (action) {
            case "add-video":
                addVideo(request,response);
                break;
            default:
                break;
        }
    }


    protected void addVideo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        log("Anhadiendo Video");  
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        //String fecha_cre = request.getParameter("fecha_de_creacion");
        //ate fecha = Date.valueOf(fecha_cre);
        String tiempo = request.getParameter("duracion");
        Time duracion = Time.valueOf(tiempo);
        String descripcion = request.getParameter("descripcion");
        String formato = request.getParameter("formato");
        String enlace = request.getParameter("enlace");
        
        String accion=request.getParameter("action");
        if(accion.equals("add-video")){
            video_reg.setTitulo(titulo);
            
            r=videoDAO_reg.validar_vid(video_reg);
            if(r==1){
                video1.setTitulo(titulo);
                video1.setAutor(autor);
                video1.setDuracion(duracion);
                video1.setDescripcion(descripcion);
                video1.setFormato(formato);
                video1.setEnlace(enlace);
                try {
                    videoDAO.registrovid(video1);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(servletRegistroVid.class.getName()).log(Level.SEVERE, null, ex);
                }                 
                //List<video> videos = videoDAO.getVideos("TITULO",titulo);
                //request.getSession().setAttribute("videos_list", videos);
                response.sendRedirect("listadoVid.jsp");
            }else{
                log("Video Repetido");
                request.getSession().removeAttribute("videos_list");
                response.sendRedirect("error_video_repetido.jsp");
            }
        } else {
            response.sendRedirect("listadoVid.jsp");
        }      
    }    


    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
