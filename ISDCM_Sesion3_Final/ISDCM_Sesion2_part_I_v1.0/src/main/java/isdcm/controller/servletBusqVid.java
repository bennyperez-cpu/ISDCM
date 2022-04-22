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
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
import isdcm.tools.File_Doc_Helper;
import isdcm.tools.Encriptacion;
import java.io.File;

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
        String       postUrl       = "http://localhost:8080/ISDCM_Sesion2_part_II_v1.0/ServletServer";// put in your url
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
            StringBuffer sb = new StringBuffer();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            log("Buscando " +  (sb.toString()));

            java.lang.reflect.Type listType = new TypeToken<ArrayList<video>>(){}.getType();
            List<video> list = new Gson().fromJson(sb.toString(), listType);
            System.out.println(gson.toJson(list));
            request.getSession().setAttribute("videos_list", list);     
        }
    }
    
        public void search_2(HttpServletRequest request, HttpServletResponse response, String parametro, String value) throws IOException {
            
            String camino_base = getClass().getProtectionDomain().getCodeSource().getLocation().getPath(); //to web folder
            String camino_final = camino_base.substring(0,camino_base.lastIndexOf("WEB-INF")).replace("%20", " ");
            
            log("Base: "+ camino_base);
            log("final: "+ camino_final);
            
            String video_name = null;

            File_Doc_Helper dh = new File_Doc_Helper();
            // Encrypt and save the video data


            
            log("Buscando por" + parametro);   
            Gson gson = new Gson();
            ISDCM_Client restClient = new ISDCM_Client();
            restClient.increReproducciones(value);

            restClient.close();

            dataJson datajson = new dataJson(parametro,value);
            //dataJson datajson = new dataJson("TITULO","La casa de Papel");
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            request.setCharacterEncoding("utf-8");
            response.setStatus(200);

            String       postUrl       = "http://localhost:8080/ISDCM_Sesion2_part_II_v1.0/ServletServer";// put in your url
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
                StringBuffer sb = new StringBuffer();
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                log("Buscando " +  (sb.toString()));

                Gson gson_1 = new Gson();

                java.lang.reflect.Type listType = new TypeToken<ArrayList<video>>(){}.getType();
                List<video> list = new Gson().fromJson(sb.toString(), listType);
                System.out.println(gson.toJson(list));

                request.getSession().setAttribute("videoDuracion", list.get(0).getDuracion());
                //request.getSession().setAttribute("videoEnlace", list.get(0).getEnlace());
                request.getSession().setAttribute("videoFormato", list.get(0).getFormato());
                request.getSession().setAttribute("videoTitulo", list.get(0).getTitulo());
                request.getSession().setAttribute("videoReproduccion", list.get(0).getReproducciones());
                
                
                video_name = list.get(0).getEnlace().substring((list.get(0).getEnlace()).indexOf("/")+1);

                //log("Nombre_vid " + video_name);// Punto de Control
                
                File localVideoFile = dh.getFile("/home/alumne/NetBeansProjects/ISDCM/ISDCM_Sesion3_Final/ISDCM_Sesion2_part_I_v1.0/src/main/webapp/Videos/" + video_name +"."+ list.get(0).getFormato());
                byte[] encryptedData = Encriptacion.encryptVideo(localVideoFile);
                dh.saveFile("/home/alumne/NetBeansProjects/ISDCM/ISDCM_Sesion3_Final/ISDCM_Sesion2_part_I_v1.0/src/main/webapp/Videos_Encriptado/"+video_name+"_encrypted.data", encryptedData);
                
                File encryptedVideoFile = dh.getFile("/home/alumne/NetBeansProjects/ISDCM/ISDCM_Sesion3_Final/ISDCM_Sesion2_part_I_v1.0/src/main/webapp/Videos_Encriptado/" + video_name + "_encrypted.data");
                byte[] decryptedData = Encriptacion.decryptVideo(encryptedVideoFile);
                dh.saveFile("/home/alumne/NetBeansProjects/ISDCM/ISDCM_Sesion3_Final/ISDCM_Sesion2_part_I_v1.0/src/main/webapp/Videos_DesEncriptado/"+video_name+"_Decrypted." + list.get(0).getFormato(), decryptedData);  
                 //log("Buscando Enlace" + video_name+"_Decrypted");
                request.getSession().setAttribute("videoEnlace", "Videos_DesEncriptado/"+video_name+"_Decrypted");
                
                
            }
        }
        
    public void show_Enlace_Data(HttpServletRequest request, HttpServletResponse response, String parameter, String enlace) throws IOException {

        Client client = ClientBuilder.newClient();
        Response response_1 = client.target("http://localhost:8080/ISDCM_Sesion2_part_II_v1.0/resources/javaee8/getVideos")
            .path("enlace").path(enlace).request(MediaType.APPLICATION_JSON).get();

     
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

/*
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/ISDCM_Sesion2/resources";

    
    public ISDCM_Client() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("javaee8");
    }

    public String increReproducciones(String enlace) throws ClientErrorException {
        Form form = new Form().param("enlace", enlace);        
        return webTarget.path("postInfo").request(javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED)
                .accept(javax.ws.rs.core.MediaType.TEXT_HTML).post(Entity.form(form), String.class);
               
    }

    public String getReproducciones(String enlace) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (enlace != null) {
            resource = resource.queryParam("enlace", enlace);
        }
        resource = resource.path("getInfo");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_HTML).get(String.class);
    }
    
    public <T> T lista_video(Class<T> responseType, String enlace) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (enlace != null) {
            resource = resource.queryParam("enlace", enlace);
        }
        resource = resource.path("getVideos");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

       
        Client client = ClientBuilder.newClient();
            Response response = client.target("http://localhost:8080/hapi_fhir_jpaserver_starter_war/fhir")
                    .path("Patient").path(patientId).request(MediaType.APPLICATION_JSON).header("Authorization", "Bearer 39ff939jgg").get();

        ObjectMapper mapper = new ObjectMapper();
        node = mapper.readTree(response.readEntity(String.class));
        System.out.println(node.get("generalPractitioner").get(0).get("identifier").get("id"));

        
        request.getSession().removeAttribute("play-video");
        response.sendRedirect("reproduccion.jsp");
        request.getSession().setAttribute("video", list);
    
    ClientConfig config = new DefaultClientConfig ();
    Client client = Client.create (config); 
    
    WebResource service = client.resource(UriBuilder.fromUri("http://192.168.1.142:8080").build ()); // getting XML data 
    String json = service.path("fhir").path("Patient/A").accept (MediaType.APPLICATION_JSON).get (String.class);
 //           System.out.println(); // getting JSON data 
 //           System.out.println(service.path("restPath").path("resourcePath").accept (MediaType.APPLICATION_XML).get (String.class));
      
       

*/