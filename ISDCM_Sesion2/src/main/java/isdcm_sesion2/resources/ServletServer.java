/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isdcm_sesion2.resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import isdcm_sesion2.modelo.dataJson;
import isdcm_sesion2.modelo.video;
import tools.videoDAO;


/**
 *
 * @author alumne
 */
@WebServlet(name = "ServletServer", urlPatterns = "/videos")
public class ServletServer extends HttpServlet {
    private static final long serialVersionUID = 1L;

    String parametro, value;

    Gson gson = new Gson();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest_1(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        response.setStatus(200);

        StringBuffer sb = new StringBuffer();
        BufferedReader br = request.getReader();

        String atributos = null;


        while((atributos = br.readLine()) != null){
            sb.append(atributos);
        }

        dataJson datajson = gson.fromJson(sb.toString(), dataJson.class);
        List<video> videos = videoDAO.getVideos(datajson.getParameter(),datajson.getValue());

        PrintWriter pw = response.getWriter();
        pw.print(gson.toJson(videos));
        pw.flush();
    }

    protected void processRequest_2(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<video> videos = videoDAO.getVideos(parametro,value);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");


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
        processRequest_1(request, response);
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
        processRequest_2(request, response);

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
