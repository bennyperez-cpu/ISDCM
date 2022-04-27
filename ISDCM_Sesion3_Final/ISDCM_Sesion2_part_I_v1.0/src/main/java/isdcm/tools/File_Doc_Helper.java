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
        File xmlFile = new File(fileName);
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        docBuilderFactory.setNamespaceAware(true);
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document document = docBuilder.parse(xmlFile);
        return document;
    }
    
    public static void escribir_document_to(Document doc, String fileName) throws Exception {
        File xmlFile = new File(fileName);
        xmlFile.createNewFile();
        TransformerFactory transformerFactory  = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory .newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(xmlFile);
        transformer.transform(source, result);
    }
}
