/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isdcm.accesscontrol.Signature_xacml;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Scanner;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author victor
 */
public class InterSignature_xacml {
    private final XMLSignature signature;
    private final KeyPair kp;
    private static final String PolicyFile = "src/xacml3_resources/policy/XACMLPolicy";
    private static final String RequestFile = "src/xacml3_resources/request/XACMLRequest";
    
     public void mainSig() throws Exception{
         Scanner reader = new Scanner(System.in);
        System.out.println("Digite un numero [1 : 3] para seleccionar la Policy,la número 4 corresponde al ejercicio 6");
        int policyNumber = reader.nextInt();
        
//        System.out.println("Digite un numero [1 : 5] para seleccionar la Request,los números 6 y 7 corresponden al ejercicio 6");
//        int requestNumber = reader.nextInt();

        String pathPolicies = PolicyFile + policyNumber + ".xml";
        System.out.println("Leyendo Policy: " + pathPolicies);
        
//        String requestFile = RequestFile + requestNumber + ".xml";
//        System.out.println("Leyendo RequestFile: " + requestFile);
        InterSignature_xacml signature = new InterSignature_xacml();
        System.out.println(signature.sign(pathPolicies));
     
     }
    
    public InterSignature_xacml() throws KeyException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        //https://www.tabnine.com/code/java/methods/javax.xml.crypto.dsig.XMLSignatureFactory/newXMLSignature
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
        kpg.initialize(2048);
        kp = kpg.generateKeyPair();
        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM"); 
        KeyInfoFactory kif = fac.getKeyInfoFactory();
        
        KeyValue kv = kif.newKeyValue(kp.getPublic());
        KeyInfo ki = kif.newKeyInfo(Collections.singletonList(kv));
        
        Reference ref = fac.newReference("", fac.newDigestMethod(DigestMethod.SHA256, null), Collections.singletonList(fac.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null)), null, null);
        SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE_WITH_COMMENTS, (C14NMethodParameterSpec) null), fac.newSignatureMethod("http://www.w3.org/2009/xmldsig11#dsa-sha256", null), Collections.singletonList(ref)); 
    
        signature = fac.newXMLSignature(si, ki);
    }
    
    public String sign(String path) throws ParserConfigurationException, FileNotFoundException, SAXException, IOException, MarshalException, XMLSignatureException, TransformerConfigurationException, TransformerException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        //https://www.javatips.net/api/javax.xml.crypto.dsig.dom.domsigncontext
        dbf.setNamespaceAware(true);
        DocumentBuilder builder = dbf.newDocumentBuilder();  
        Document doc = builder.parse(new FileInputStream(path));
        DOMSignContext dsc = new DOMSignContext(kp.getPrivate(), doc.getDocumentElement()); 
        signature.sign(dsc);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer trans = tf.newTransformer();
        trans.transform(new DOMSource(doc), new StreamResult(stream));
        
        return stream.toString();
    }
    
    
}
