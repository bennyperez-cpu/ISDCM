/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.helpers.Security;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.util.Collections;
import static java.util.Collections.singletonList;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import static javax.xml.crypto.dsig.CanonicalizationMethod.EXCLUSIVE;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import static javax.xml.crypto.dsig.Transform.ENVELOPED;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import javax.xml.crypto.KeySelector;

/**
 *
 * @author nfran
 */
public class SecurityHelper {
    public static Document signDocument(Document doc) throws NoSuchAlgorithmException, ClassNotFoundException, InstantiationException, InvalidAlgorithmParameterException, TransformerException, FileNotFoundException, KeyException, IllegalAccessException, MarshalException, XMLSignatureException {

        String providerName = System.getProperty("jsr105Provider", "org.jcp.xml.dsig.internal.dom.XMLDSigRI");

        XMLSignatureFactory fac;
        fac = XMLSignatureFactory.getInstance("DOM", (Provider) Class.forName(providerName).newInstance());

        DigestMethod digestMethod = fac.newDigestMethod(DigestMethod.SHA256, null);
        Transform transform = fac.newTransform(ENVELOPED, (TransformParameterSpec) null);
        Reference reference = fac.newReference("", digestMethod, singletonList(transform), null, null);
        SignatureMethod signatureMethod = fac.newSignatureMethod("http://www.w3.org/2001/04/xmldsig-more#rsa-sha256", null);
        CanonicalizationMethod canonicalizationMethod = fac.newCanonicalizationMethod(EXCLUSIVE, (C14NMethodParameterSpec) null);

        // Create the SignedInfo
        SignedInfo si = fac.newSignedInfo(canonicalizationMethod, signatureMethod, singletonList(reference));


        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);

        KeyPair kp = kpg.generateKeyPair();

        KeyInfoFactory kif;
        kif = fac.getKeyInfoFactory();
        KeyValue kv = kif.newKeyValue(kp.getPublic());

        // Create a KeyInfo and add the KeyValue to it
        KeyInfo ki;
        ki = kif.newKeyInfo(Collections.singletonList(kv));
        DOMSignContext dsc;
        dsc = new DOMSignContext(kp.getPrivate(), doc.getDocumentElement());

        XMLSignature signature;
        signature = fac.newXMLSignature(si, ki);
        signature.sign(dsc);
       
        return doc;

    }
    
    public static boolean validateDocument(Document doc) throws Exception{
        NodeList nl = doc.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");
        if (nl.getLength() == 0) {
          throw new Exception("Cannot find Signature element");
        } 
        //Validation context
        DOMValidateContext valContext;
        valContext = new DOMValidateContext(new KeyValueKeySelector(), nl.item(0));
        
        XMLSignatureFactory factory = XMLSignatureFactory.getInstance("DOM");
        XMLSignature signature = factory.unmarshalXMLSignature(valContext);
        
        return signature.validate(valContext);
    }
    
}

