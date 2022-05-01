package isdcm.tools;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 *
 * @author Benny Hammer Pérez Vásquez
 */

public class File_Doc_Helper {   
    
    public static Document cargar_document_from(String fileName) throws Exception {
        //https://www.tabnine.com/code/java/classes/javax.xml.parsers.DocumentBuilderFactory
        //https://www.tabnine.com/code/java/methods/javax.xml.parsers.DocumentBuilderFactory/newDocumentBuilder
        File xmlFile = new File(fileName);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(xmlFile);
        return document;
    }
    
    public static void escribir_document_to(Document out, String fileName) throws Exception {
        //https://www.tabnine.com/code/java/methods/javax.xml.transform.TransformerFactory/newInstance
        //https://www.tabnine.com/code/java/methods/javax.xml.transform.stream.StreamResult/getWriter
        File xmlFile = new File(fileName);
        xmlFile.createNewFile();
        TransformerFactory transformerFactory  = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory .newTransformer();
        StreamResult result = new StreamResult(xmlFile);
        DOMSource source = new DOMSource(out);
        transformer.transform(source, result);
    }
}
