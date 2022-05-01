/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isdcm.tools;

import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.w3c.dom.Document;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Benny Hammer Pérez Vásquez
 */
public class serializador {
   
    
    public static void serializadorTool(Document finalDocument, HttpServletResponse response) throws IOException{
        //https://www.tabnine.com/code/java/methods/org.apache.xml.serialize.XMLSerializer/setOutputByteStream
        //https://stackoverflow.com/questions/8293109/serialize-a-document-object-in-java-while-preserving-the-formatting-of-arbitrar
        response.setContentType( "text/xml" );
        XMLSerializer serializer = new XMLSerializer();
        serializer.setOutputByteStream(response.getOutputStream());
        serializer.serialize(finalDocument);
    }
    
}
