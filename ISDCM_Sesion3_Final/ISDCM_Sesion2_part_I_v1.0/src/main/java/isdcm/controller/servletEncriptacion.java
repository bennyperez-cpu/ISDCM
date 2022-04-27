
package isdcm.controller;

import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.w3c.dom.Document;

import isdcm.tools.File_Doc_Helper;
import isdcm.tools.Encriptacion;

/**
 *
 * @author Benny Hammer Pérez Vásquez
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
            String destFilename = request.getParameter("File_Destin");
            String destFilename_2 = request.getParameter("File_Destin_2");
            Document finalDocument;
            log("Desencriptando");
            // Load the encrypted version of didlFilm1.xml(didlFilm1Encrypted.xml)
            Document encryptedDocument =  File_Doc_Helper.cargar_document_from(destFilename);
            // Get the decrypted document
            finalDocument = Encriptacion.xml_DesEncriptaccion(encryptedDocument);
            File_Doc_Helper.escribir_document_to(finalDocument,destFilename_2);

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
            String File_source = request.getParameter("File_source");
            String File_Destin = request.getParameter("File_Destin");
            String action = request.getParameter("action");
            
            
            Document finalDocument;
            log(action);
            log(File_source);
            
            log("Encriptando");
            Document originalXML =  File_Doc_Helper.cargar_document_from(File_source);
            finalDocument = Encriptacion.xml_Encriptacion(originalXML, false);
            File_Doc_Helper.escribir_document_to(finalDocument,File_Destin);
            
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
