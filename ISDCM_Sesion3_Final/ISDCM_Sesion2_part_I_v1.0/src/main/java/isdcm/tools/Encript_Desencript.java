
package isdcm.tools;


import org.apache.xml.security.encryption.XMLCipher;
import java.io.File;
import java.nio.file.Files;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.crypto.Cipher;

/**
 *
 * @author Benny Hammer Pérez Vásquez
 */
public class Encript_Desencript {

    static{
        org.apache.xml.security.Init.init();
    }
    public static Document xml_Encriptacion(Document document, boolean encryptContentsOnly) throws Exception {
         //https://www.baeldung.com/java-aes-encryption-decryption
         //https://www.tabnine.com/code/java/classes/org.apache.xml.security.encryption.XMLCipher
         //https://www.adictosaltrabajo.com/2005/11/24/xmlenc/
        String llave = "llave_gen_manual";//bad_practice_key
        SecretKey llave_Simetrica = new SecretKeySpec(llave.getBytes(), "AES");
        XMLCipher xmlCipher = XMLCipher.getInstance(XMLCipher.AES_128);
        xmlCipher.init(XMLCipher.ENCRYPT_MODE, llave_Simetrica);
        Document xml_Encriptado = xmlCipher.doFinal(document, (Element)document.getDocumentElement(),encryptContentsOnly);
        return xml_Encriptado;
    }
    
    public static Document xml_DesEncriptacion(Document document) throws Exception {
         //https://www.baeldung.com/java-aes-encryption-decryption
         //https://www.tabnine.com/code/java/classes/org.apache.xml.security.encryption.XMLCipher
        String llave = "llave_gen_manual";
        SecretKey llave_Simetrica = new SecretKeySpec(llave.getBytes(), "AES");
        XMLCipher xmlCipher = XMLCipher.getInstance(XMLCipher.AES_128);
        xmlCipher.init(XMLCipher.DECRYPT_MODE, llave_Simetrica);
        Document xml_Desencriptado = xmlCipher.doFinal(document, (Element)document.getDocumentElement());
        return xml_Desencriptado;
    }
    

    public static byte[] video_Encriptacion(File file){
        //https://www.baeldung.com/java-aes-encryption-decryption
        //https://www.tabnine.com/code/java/methods/javax.crypto.Cipher/doFinal
        String llave = "llave_gen_manual";
        SecretKey llave_Simetrica = new SecretKeySpec(llave.getBytes(), "AES");
        Cipher aesCipher;
        try {
            aesCipher = Cipher.getInstance("AES");
            aesCipher.init(Cipher.ENCRYPT_MODE,llave_Simetrica);            
            byte[] data = Files.readAllBytes(file.toPath());
            byte[] Data_Encriptada = aesCipher.doFinal(data);
            return Data_Encriptada;  
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    public static byte[] video_Desencriptacion(File encryptedFile){
        //https://www.baeldung.com/java-aes-encryption-decryption
        //https://www.tabnine.com/code/java/methods/javax.crypto.Cipher/doFinal
        String llave = "llave_gen_manual";
        SecretKey llave_Simetrica = new SecretKeySpec(llave.getBytes(), "AES");
        Cipher des_aesCipher;
        try{
            des_aesCipher  = Cipher.getInstance("AES");
            des_aesCipher.init(Cipher.DECRYPT_MODE,llave_Simetrica);
            byte[] File_to_data_Encriptacion = Files.readAllBytes(encryptedFile.toPath());       
            byte[] Data_Desencriptada = des_aesCipher.doFinal(File_to_data_Encriptacion);          
            return Data_Desencriptada;
        } catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    
    
}
