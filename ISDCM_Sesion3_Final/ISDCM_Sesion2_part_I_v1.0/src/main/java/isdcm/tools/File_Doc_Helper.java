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

public class File_Doc_Helper {
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
    
    public File getFile(String path){
        return new File(path);//finalPath + File.separator + path
    }
    
    public void saveFile(String path, byte[] data) throws IOException{
        File fileToSave = new File(path);//finalPath + File.separator+ path
        Files.write(fileToSave.toPath(), data);
    }
}
