/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isdcm.controller;

import isdcm.model.usuario;
import isdcm.tools.usuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Benny Hammer Pérez Vásquez
 */
@WebServlet(name = "servletUsuarios", urlPatterns = {"/servletUsuarios"})
public class servletUsuarios extends HttpServlet {
    usuarioDAO dao = new usuarioDAO();
    usuarioDAO dao_reg = new usuarioDAO();
    usuario usu=new usuario();
    usuario usu_reg = new usuario();    
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
        String accion=request.getParameter("accion");
            if(accion.equals("Ingresar")){
                String nombre=request.getParameter("txtnom");
                String contrasenha=request.getParameter("password");
                usu.setNombre_de_usuario(nombre);
                usu.setContrasenha(contrasenha);
                r=dao.validar(usu);
                if(r==1){
                    request.getSession().setAttribute("userName",nombre);
                    request.getRequestDispatcher("listadoVid.jsp").forward(request, response);
                }else{
        
                    request.getRequestDispatcher("error_login.jsp").forward(request, response);
                }
            } else {
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        
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
        String nombre=request.getParameter("name");
        String apellido=request.getParameter("surname");
        String correo_electronico=request.getParameter("email");
        String nombre_usuario=request.getParameter("username");
        String contrasenha=request.getParameter("password");
        String contrasenha2=request.getParameter("password2");
        String accion=request.getParameter("action");
            if(accion.equals("register")){
                usu_reg.setNombre_de_usuario(nombre_usuario);

                r=dao_reg.validar_reg(usu_reg);
                if(r==0){
                    if(contrasenha.equals(contrasenha2)){

                        usuario usu1 = new usuario();
                        usu1.setNombre(nombre);
                        usu1.setApellido(apellido);
                        usu1.setCorreo_electronico(correo_electronico);
                        usu1.setNombre_de_usuario(nombre_usuario);
                        usu1.setContrasenha(contrasenha);
                        try {
                            dao.registerusu(usu1);
                        } catch (Exception e) {
                            //TODO: handle exception
                            e.printStackTrace();
                        }    
                        response.sendRedirect("login.jsp");
                    } else {
                        RequestDispatcher rd = request.getRequestDispatcher("registroUsu.jsp");
                        rd.forward(request, response);
                    }
                }else{
                    response.sendRedirect("error_ingreso_de_datos.jsp");
                }
            } else {
                response.sendRedirect("registroUsu.jsp");
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
