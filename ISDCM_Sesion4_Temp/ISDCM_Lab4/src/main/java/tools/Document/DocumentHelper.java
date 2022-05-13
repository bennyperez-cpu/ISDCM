/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.Document;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Element;

/**
 *
 * @author Benny Hammer Pérez Vasquez
 */
public class DocumentHelper {

    private final String basePath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath(); //to web folder
    private final String finalPath = basePath.substring(0,basePath.lastIndexOf("WEB-INF")).replace("%20", " ");
    
    public static Document loadDocumentFromFile(String fileName) throws Exception {
        File xmlFile = new File(fileName);
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        docBuilderFactory.setNamespaceAware(true);
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document document = docBuilder.parse(xmlFile);
        return document;
    }
    
    public static void writeDocumentToFile(Document doc, String fileName) throws Exception {
        File xmlFile = new File(fileName);
        xmlFile.createNewFile();
        TransformerFactory transformerFactory  = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory .newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(xmlFile);
        transformer.transform(source, result);
    }
    
    public static byte[] getPolicyBytes(Document doc) throws TransformerConfigurationException, TransformerException {
        
        TransformerFactory transformerFactory  = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        //we omit the header !!!
        transformer.setOutputProperty("omit-xml-declaration", "yes"); 
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        StreamResult result=new StreamResult(bos);
        DOMSource source = new DOMSource(doc);
        transformer.transform(source, result);
        return bos.toByteArray();
    }
}
