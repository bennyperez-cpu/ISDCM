/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isdcm.tools;

import javax.crypto.SecretKey;

import org.w3c.dom.Document;

import org.apache.xml.security.encryption.XMLCipher;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author alumne
 */
public class Encriptacion {

    static{
        org.apache.xml.security.Init.init();
    }
    public static Document getEncryptedDocument(Document document, boolean encryptContentsOnly) throws Exception {
         /* Academic purpose only, the propper way would be:
            1-Generate the symmetric keys with a keygen algorithm using different random salts per document instance.
            2-Also the symmetric key should be encrypted and added as metadata in the encrypted file.
            3-Keys for metadata desencription should be stored as they'll be necessary 
            to retrieve the symmetrickey in order to succesfully decrypt document's content
        */
        String key = "bad_practice_key";
        SecretKey symmetricKey = new SecretKeySpec(key.getBytes(), "AES");
        XMLCipher xmlCipher = XMLCipher.getInstance(XMLCipher.AES_128);
        xmlCipher.init(XMLCipher.ENCRYPT_MODE, symmetricKey);
        Document encryptedDocument = xmlCipher.doFinal(document, (Element)document.getDocumentElement(),encryptContentsOnly);
        return encryptedDocument;
    }
    
    public static Document getDecryptedDocument(Document document) throws Exception {
         /* Academic purpose only, the propper way would be:
            1-Get encrypted symmetric key from the encrypted document's metadata
            2-Retrieve keys from somewhere to obtain decrypt the metadata and obtain the symmetric key
            3-Use that symmetric key to succesfully decrypt the encrypted document
        */
        String key = "bad_practice_key";
        SecretKey symmetricKey = new SecretKeySpec(key.getBytes(), "AES");
        XMLCipher xmlCipher = XMLCipher.getInstance(XMLCipher.AES_128);
        xmlCipher.init(XMLCipher.DECRYPT_MODE, symmetricKey);
        Document decryptedDocument = xmlCipher.doFinal(document, (Element)document.getDocumentElement());
        return decryptedDocument;
        
    }
    
    /* Reads the video file located at @path, encrypts its bytes using AES cipher and saves the resulted encrypted
       bytes into a new file located in the web/ folder "encrypted.data"
    */
    public static byte[] encryptVideo(File file){
        //Generate the key
        String key = "bad_practice_key";
        SecretKey symmetricKey = new SecretKeySpec(key.getBytes(), "AES");

        Cipher encryptingCipher;
        // Encrypt the video
        try {
            encryptingCipher = Cipher.getInstance("AES");
            encryptingCipher.init(Cipher.ENCRYPT_MODE,symmetricKey);
            
            byte[] data = Files.readAllBytes(file.toPath());
            byte[] encryptedData = encryptingCipher.doFinal(data);
           
            return encryptedData;
            
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | IOException ex) {
            Logger.getLogger(Encriptacion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    /* Reads the contents of the file with the encrypted data located in the web/ folder "encrypted.data", decrypts 
       its bytes using AES cipher and saves the resulted video file into a new file in the web/videos/ folder with
       its proper format. Video Player will load this file.
    */
    public static byte[] decryptVideo(File encryptedFile){
        //Generate the key
        String key = "bad_practice_key";
        SecretKey symmetricKey = new SecretKeySpec(key.getBytes(), "AES");

        Cipher decryptingCipher;
        
        // Decrypt the data
        try{
            decryptingCipher  = Cipher.getInstance("AES");
            decryptingCipher.init(Cipher.DECRYPT_MODE,symmetricKey);
        
            //Decrypt the data
            byte[] encryptedDataFromEncryptedFile = Files.readAllBytes(encryptedFile.toPath());
            
            byte[] decryptedData = decryptingCipher.doFinal(encryptedDataFromEncryptedFile);
            
            return decryptedData;
             
        } catch(Exception e){
            System.out.print(e.getMessage());
            return null;
        }
    }
    
    
}
