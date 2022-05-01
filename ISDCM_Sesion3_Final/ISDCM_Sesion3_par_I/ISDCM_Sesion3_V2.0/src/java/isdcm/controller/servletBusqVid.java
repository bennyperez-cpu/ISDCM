/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isdcm.controller;

import isdcm.model.usuario;
import isdcm.model.video;
import isdcm.tools.videoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alumne
 */
@WebServlet(name = "servletBusqVid", urlPatterns = {"/servletBusqVid"})
public class servletBusqVid extends HttpServlet {

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
        processRequest(request, response);
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
                String parametro;
        
                switch (action) {
                    case "search-autor":
                        parametro = "AUTOR";
                        search(request,response,parametro);
                        break;
                    case "search-titulo":
                        parametro = "TITULO";
                        search(request,response,parametro);
                        break;
                    case "search-fecha":
                        parametro = "FECHA_DE_CREACION";
                        search(request,response,parametro);
                        break;
                    case "change-search":
                        log("change-search");
                        request.getSession().removeAttribute("videos_list");
                        response.sendRedirect("listadoVid.jsp");
                        break;
                    
                    default:
                        break;
                }
    }
    
private void search(HttpServletRequest request, HttpServletResponse response, String parametro) throws IOException {
    log("Buscando por" + parametro);              
    List<video> videos = videoDAO.getVideos(parametro);
    request.getSession().setAttribute("videos_list", videos);
    response.sendRedirect("listadoVid.jsp");

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
