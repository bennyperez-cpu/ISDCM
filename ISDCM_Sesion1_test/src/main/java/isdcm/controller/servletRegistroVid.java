/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isdcm.controller;

import isdcm.model.video;
import isdcm.tools.videoDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alumne
 */
public class servletRegistroVid extends HttpServlet {

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

    public void addVideo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        log("video_added");
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        String fecha_cre = request.getParameter("fecha");
        Date fecha = Date.valueOf(fecha_cre);
        String tiempo = request.getParameter("duracion");
        Time duracion = Time.valueOf(tiempo);
        String descripcion = request.getParameter("descripcion");
        String formato = request.getParameter("formato");
        
        video video1 = new video(titulo,autor,fecha,duracion,descripcion,formato);
        videoDAO.registrovid(video1);
        List<video> videos = videoDAO.getVideos();
        request.getSession().setAttribute("videos", videos);
        response.sendRedirect("logged.jsp");
        
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
