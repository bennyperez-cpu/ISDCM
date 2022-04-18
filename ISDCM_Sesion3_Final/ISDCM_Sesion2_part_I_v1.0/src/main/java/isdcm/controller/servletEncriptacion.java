/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isdcm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.xml.serializer;
import org.apache.xml.serializer.XMLSerializer;

import com.sun.org.apache.xml.internal.serialize.XMLSerializer;


import org.w3c.dom.Document;

import isdcm.tools.DocumentHelper;
import isdcm.tools.Encriptacion;

/**
 *
 * @author alumne
 */
public class servletEncriptacion extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try {
            request.getSession().setAttribute("infoLabel", "");
            String srcFilename = request.getParameter("srcFilename");
            String action = request.getParameter("action");
            String destFilename = request.getParameter("destFilename");
            Document finalDocument;
            log(action);
            log(srcFilename);
            log("decrypting");
            // Load the encrypted version of didlFilm1.xml(didlFilm1Encrypted.xml)
            Document encryptedDocument =  DocumentHelper.loadDocumentFromFile(destFilename);
            // Get the decrypted document
            finalDocument = Encriptacion.getDecryptedDocument(encryptedDocument);

            log("IN");
            response.setContentType( "text/xml" );
            XMLSerializer serializer = new XMLSerializer();
            serializer.setOutputByteStream(response.getOutputStream());
            serializer.serialize(finalDocument);
        } catch (Exception e) {
            request.getSession().setAttribute("infoLabel", "Revisa si es que el archivo está en la ruta especificada");
            response.sendRedirect("seguridad.jsp");
        }
    }

    protected void processRequest_2(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            request.getSession().setAttribute("infoLabel", "");
            String srcFilename = request.getParameter("srcFilename");
            String action = request.getParameter("action");
            String destFilename = request.getParameter("destFilename");
            Document finalDocument;
            log(action);
            log(srcFilename);
            
            log("encrypting");
            // Load the original(not encrypted) version of didlFilm1.xml
            Document originalDocument =  DocumentHelper.loadDocumentFromFile(srcFilename);
            // Get the encrypted document
            finalDocument = Encriptacion.getEncryptedDocument(originalDocument, false);
            DocumentHelper.writeDocumentToFile(finalDocument,destFilename);
            
            response.setContentType( "text/xml" );
            XMLSerializer serializer = new XMLSerializer();
            serializer.setOutputByteStream(response.getOutputStream());
            serializer.serialize(finalDocument);
        } catch (Exception e) {
            request.getSession().setAttribute("infoLabel", "There was an error encripting the document. Please check it exists in the specified path.");
            response.sendRedirect("security.jsp");
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
