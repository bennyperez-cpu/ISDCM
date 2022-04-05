/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isdcm.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import isdcm.model.dataJson;
import isdcm.model.video;
import isdcm.tools.videoDAO;
import isdcm.isdcm_sesion1.resources.*;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.lang.ProcessBuilder.Redirect.Type;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 *
 * @author alumne
 */
public class servletBusqVid extends HttpServlet {
    private static final long serialVersionUID = 1L;

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
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                System.out.println(request);
                String action = request.getParameter("action");
                
                String parametro;
                String value;
        
                switch (action) {
                    case "search-autor":
                        parametro = "AUTOR";
                        value = request.getParameter("author");
                        search(request,response,parametro,value);
                        response.sendRedirect("listadoVid.jsp");
                        break;
                    case "search-titulo":
                        parametro = "TITULO";
                        value = request.getParameter("title");
                        search(request,response,parametro,value);
                        response.sendRedirect("listadoVid.jsp");
                        break;
                    case "search-fecha":
                        parametro = "FECHA_DE_CREACION";
                        value = request.getParameter("date");
                        search(request,response,parametro,value);
                        response.sendRedirect("listadoVid.jsp");
                        break;
                    case "change-search":
                        log("change-search");
                        request.getSession().removeAttribute("videos_list");
                        response.sendRedirect("listadoVid.jsp");
                        break;

                    case "play-video":
                        log("play-video");                 
                        value = request.getParameter("videoEnlace");
                        parametro = "ENLACE";
                        log("Buscando por " + value);
                        search_2(request,response,parametro,value);
                        response.sendRedirect("reproduccion.jsp");
                        break;
                    
                    default:
                        break;
                }
            }
        
    public void search(HttpServletRequest request, HttpServletResponse response, String parametro, String value) throws IOException {
        log("Buscando por" + parametro);              
        //List<video> videos = videoDAO.getVideos(parametro,value);

        Gson gson = new Gson();
        dataJson datajson = new dataJson(parametro,value);
        //dataJson datajson = new dataJson("TITULO","La casa de Papel");
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        response.setStatus(200);
        
        String       postUrl       = "http://localhost:8080/ISDCM_Sesion2/ServletServer";// put in your url

        HttpClient   httpClient    = HttpClientBuilder.create().build();
        HttpPost     post          = new HttpPost(postUrl);
        StringEntity postingString = new StringEntity(gson.toJson(datajson));//gson.tojson() converts your pojo to json
        post.setEntity(postingString);
        post.setHeader("Content-type", "application/json");
        HttpResponse  response_2 = httpClient.execute(post);
        //httpClient.execute(post);
        log("Buscando por final" );
        
        HttpEntity entity = response_2.getEntity();

        if (entity != null) {

            InputStreamReader reader = new InputStreamReader(entity.getContent());
            BufferedReader br = new BufferedReader(reader);

            //BufferedReader br = request.getReader();
            StringBuffer sb = new StringBuffer();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            log("Buscando " +  (sb.toString()));

            Gson gson_1 = new Gson();

     //   video[] videos = gson_1.fromJson(new JsonReader(new FileReader(sb.toString())), video[].class);
            java.lang.reflect.Type listType = new TypeToken<ArrayList<video>>(){}.getType();
            List<video> list = new Gson().fromJson(sb.toString(), listType);
        
            //video[] videos = gson.fromJson(sb.toString(), video[].class);
            System.out.println(gson.toJson(list));
        
            request.getSession().setAttribute("videos_list", list);
            
        }

        /*PrintWriter pw = response.getWriter();
        pw.print(gson.toJson(response_2));
        pw.flush();*/

/*
        StringBuffer sb = new StringBuffer();
        BufferedReader br = request.getReader();

        String atributos = null;


        while((atributos = br.readLine()) != null){
            sb.append(atributos);
        
        }*/
    }
    
        public void search_2(HttpServletRequest request, HttpServletResponse response, String parametro, String value) throws IOException {
        log("Buscando por" + parametro);              
        //List<video> videos = videoDAO.getVideos(parametro,value);

        Gson gson = new Gson();
        dataJson datajson = new dataJson(parametro,value);
        //dataJson datajson = new dataJson("TITULO","La casa de Papel");
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        response.setStatus(200);
        
        String       postUrl       = "http://localhost:8080/ISDCM_Sesion2/ServletServer";// put in your url

        HttpClient   httpClient    = HttpClientBuilder.create().build();
        HttpPost     post          = new HttpPost(postUrl);
        StringEntity postingString = new StringEntity(gson.toJson(datajson));//gson.tojson() converts your pojo to json
        post.setEntity(postingString);
        post.setHeader("Content-type", "application/json");
        HttpResponse  response_2 = httpClient.execute(post);
        //httpClient.execute(post);
        log("Buscando por final" );
        
        HttpEntity entity = response_2.getEntity();

        if (entity != null) {

            InputStreamReader reader = new InputStreamReader(entity.getContent());
            BufferedReader br = new BufferedReader(reader);

            //BufferedReader br = request.getReader();
            StringBuffer sb = new StringBuffer();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            log("Buscando " +  (sb.toString()));

            Gson gson_1 = new Gson();

     //   video[] videos = gson_1.fromJson(new JsonReader(new FileReader(sb.toString())), video[].class);
            java.lang.reflect.Type listType = new TypeToken<ArrayList<video>>(){}.getType();
            List<video> list = new Gson().fromJson(sb.toString(), listType);
            
            System.out.println(gson.toJson(list));
                      
          
            String temp = list.get(0).getTitulo();
            request.getSession().setAttribute("videoTitulo", list.get(0).getTitulo());
            request.getSession().setAttribute("videoDuracion", list.get(0).getDuracion());
            request.getSession().setAttribute("videoEnlace", list.get(0).getEnlace());
            request.getSession().setAttribute("videoTitulo", list.get(0).getTitulo());
            request.getSession().setAttribute("videoReproduccion", list.get(0).getReproducciones());
        }

        /*PrintWriter pw = response.getWriter();
        pw.print(gson.toJson(response_2));
        pw.flush();*/

/*
        StringBuffer sb = new StringBuffer();
        BufferedReader br = request.getReader();

        String atributos = null;


        while((atributos = br.readLine()) != null){
            sb.append(atributos);
        
        }*/
    }
        
     /*       
    public void show_visualization(HttpServletRequest request, HttpServletResponse response, String parameter, String enlace) throws IOException {
       
        //String location = request.getParameter("location");
        

        Gson gson = new Gson();
 
        dataJson datajson = new dataJson(parameter,enlace);
        //dataJson datajson = new dataJson("TITULO","La casa de Papel");
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        response.setStatus(200);
        
        String       postUrl       = "http://localhost:8080/ISDCM_Sesion2/ServletServer";// put in your url

        HttpClient   httpClient    = HttpClientBuilder.create().build();
        HttpPost     post          = new HttpPost(postUrl);
        StringEntity postingString = new StringEntity(gson.toJson(datajson));//gson.tojson() converts your pojo to json
        post.setEntity(postingString);
        post.setHeader("Content-type", "application/json");
        HttpResponse  response_2 = httpClient.execute(post);
        //httpClient.execute(post);
        log("Buscando por final" );
        
        HttpEntity entity = response_2.getEntity();

        if (entity != null) {

            InputStreamReader reader = new InputStreamReader(entity.getContent());
            BufferedReader br = new BufferedReader(reader);

            //BufferedReader br = request.getReader();
            StringBuffer sb = new StringBuffer();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            log("Buscando " +  (sb.toString()));

            Gson gson_1 = new Gson();

     //   video[] videos = gson_1.fromJson(new JsonReader(new FileReader(sb.toString())), video[].class);
            java.lang.reflect.Type listType = new TypeToken<ArrayList<video>>(){}.getType();
            List<video> list = new Gson().fromJson(sb.toString(), listType);
        
        log("El resultato 1 y 2" + list);
        
        

        
        request.getSession().removeAttribute("play-video");
        response.sendRedirect("reproduccion.jsp");
        request.getSession().setAttribute("video", list);
        
        }
    }  */  

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
