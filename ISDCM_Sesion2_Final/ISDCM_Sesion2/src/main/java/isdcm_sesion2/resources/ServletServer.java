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
        String parametro, value;
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        response.setStatus(200);
/*
        StringBuilder sb = new StringBuilder();
        BufferedReader br = request.getReader();

        String atributos = null;

        while((atributos = br.readLine()) != null){
            sb.append(atributos);
        }

        dataJson datajson = gson.fromJson(sb.toString(), dataJson.class);
        List<video> videos = videoDAO.getVideos(datajson.getParameter(),datajson.getValue());*/
        List<video> videos = videoDAO.getVideos("TITULO","La casa de Papel");

        PrintWriter pw = response.getWriter();
        pw.print(gson.toJson(videos));
        pw.flush();
    }

    protected void processRequest_2(HttpServletRequest request, HttpServletResponse response)
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
    //    processRequest_1(request, response);
    
    /*    
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.setStatus(200);
    
        
        dataJson data = new dataJson("Hola","Mundo");
        
        PrintWriter pw = response.getWriter();
        pw.print(gson.toJson(data));
        pw.flush();
    */
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        response.setStatus(200);
        
        String atributos = null;
        StringBuilder sb = new StringBuilder();

        BufferedReader br = request.getReader();

        while((atributos = br.readLine()) != null){
            sb.append(atributos);
        }

        dataJson datajson = gson.fromJson(sb.toString(), dataJson.class);
        List<video> videos = videoDAO.getVideos(datajson.getParameter(),datajson.getValue());
        //List<video> videos = videoDAO.getVideos("TITULO","La casa de Papel");

        PrintWriter pw = response.getWriter();
        pw.print(gson.toJson(videos));
        pw.flush();
        
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
                response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        response.setStatus(201);
        
        String atributos = null;
        StringBuffer sb = new StringBuffer();

        BufferedReader br = request.getReader();

        while((atributos = br.readLine()) != null){
            sb.append(atributos);
        }

        dataJson datajson = gson.fromJson(sb.toString(), dataJson.class);
        log("Buscando por " + datajson.getValue()); 
        List<video> videos = videoDAO.getVideos(datajson.getParameter(),datajson.getValue());
        //List<video> videos = videoDAO.getVideos("TITULO","La casa de Papel");

        PrintWriter pw = response.getWriter();
        pw.print(gson.toJson(videos));
       //pw.print(gson.toJson(videoDAO.getVideos("TITULO","La casa de Papel")));
        pw.flush();
        
        




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
