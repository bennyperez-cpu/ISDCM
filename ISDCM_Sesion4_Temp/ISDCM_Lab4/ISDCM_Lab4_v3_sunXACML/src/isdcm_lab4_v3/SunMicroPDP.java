/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isdcm_lab4_v3;

/**
 *
 * @author alumne
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sun.xacml.PDP;
import com.sun.xacml.PDPConfig;
import com.sun.xacml.ParsingException;
import com.sun.xacml.PolicySet;
import com.sun.xacml.cond.FunctionFactory;
import com.sun.xacml.cond.FunctionFactoryProxy;
import com.sun.xacml.cond.StandardFunctionFactory;
import com.sun.xacml.ctx.RequestCtx;
import com.sun.xacml.ctx.ResponseCtx;
import com.sun.xacml.finder.AttributeFinder;
import com.sun.xacml.finder.PolicyFinder;
import com.sun.xacml.finder.impl.CurrentEnvModule;
import com.sun.xacml.finder.impl.FilePolicyModule;
import com.sun.xacml.finder.impl.SelectorModule;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Transformer;
import javax.xml.transform.stream.StreamResult;
import tools.File_Doc_Helper;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyException;
import java.security.NoSuchAlgorithmException;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
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
 * @author nfran
 */
public class SunMicroPDP {
    private PDP pdp = null;
    
    public SunMicroPDP(ArrayList<String> policyFiles) throws Exception {
        FilePolicyModule filePolicyModule = new FilePolicyModule();
        
        String policySetFile = setPolicyFiles(policyFiles);
        filePolicyModule.addPolicy(policySetFile);

        PolicyFinder policyFinder = new PolicyFinder();
        Set policyModules = new HashSet();
        policyModules.add(filePolicyModule);
        policyFinder.setModules(policyModules);

        CurrentEnvModule envAttributeModule = new CurrentEnvModule();
        SelectorModule selectorAttributeModule = new SelectorModule();

        AttributeFinder attributeFinder = new AttributeFinder();
        List attributeModules = new ArrayList();
        attributeModules.add(envAttributeModule);
        attributeModules.add(selectorAttributeModule);
        attributeFinder.setModules(attributeModules);

        pdp = new PDP(new PDPConfig(attributeFinder, policyFinder, null));
    }
    
    public ResponseCtx evaluate(String requestFile) throws FileNotFoundException, ParsingException
    {
        RequestCtx request = RequestCtx.getInstance(new FileInputStream(requestFile));
        return pdp.evaluate(request);
    }
    
    
    // Copy the selected policies to a new policySet file so Sun can load that file with the selected policies 
    private String setPolicyFiles(ArrayList<String> selectedPolicyFiles) throws Exception{
        try {
            String routeXMLFile = "src/resources/support-xacml-2-0/support/policy/XACMLPolicySet.xml";
            File f = new File(routeXMLFile);
            
            String basePath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();

            System.out.println(basePath);

            System.out.println(f.toPath());
           
            
            
            if (f.exists())   Files.walk(f.toPath()).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
            Files.createFile(f.toPath());
            
            //Header
            String header = "<PolicySet xmlns=\"urn:oasis:names:tc:xacml:1.0:core:schema:wd-17\"  PolicyCombiningAlgId=\"urn:oasis:names:tc:xacml:1.0:policy-combining-algorithm:deny-overrides\" PolicySetId=\"PolicySetExample\" Version=\"1.0\">\n" +
"    <Target>\n" +
"        <Subject>\"Role\"</Subject>\n" +
"    </Target>\n";
            Files.write(f.toPath(), header.getBytes());
            
            
            // https://docs.oracle.com/cd/E17802_01/webservices/webservices/docs/1.5/xmldsig/api/javax/xml/crypto/doc-files/Validate.java
            String signedPolicyFilename = "src/resources/support-xacml-2-0/support/policy/signedPolicy.xml";
            //Policies
            for (int i = 0; i < selectedPolicyFiles.size(); ++i){
                //Load unsigned policy into a DOM document
                Document policyDoc = File_Doc_Helper.cargar_document_from(selectedPolicyFiles.get(i));
                //Sign that policy document
                sign(policyDoc);
                //Store signed policy document as signedPolicy.xml
                File_Doc_Helper.escribir_document_to(policyDoc,signedPolicyFilename);
                
                //Load signed policy into DOM document (signedPolicy.xml)
                File_Doc_Helper.cargar_document_from(signedPolicyFilename);
                
                TransformerFactory transformerFactory  = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                //we omit the header !!!
                transformer.setOutputProperty("omit-xml-declaration", "yes"); 
                ByteArrayOutputStream bos=new ByteArrayOutputStream();
                StreamResult result=new StreamResult(bos);
                DOMSource source = new DOMSource(policyDoc);
                transformer.transform(source, result);
                byte[] policyBytes = bos.toByteArray();
                
                 
                Files.write(f.toPath(),policyBytes,StandardOpenOption.APPEND);
                Files.write(f.toPath(),"\n".getBytes(),StandardOpenOption.APPEND);
                
            }
             
            String end = "</PolicySet>";
            Files.write(f.toPath(),end.getBytes(),StandardOpenOption.APPEND);

           
            
            return routeXMLFile;
        } catch (IOException ex) {
            Logger.getLogger(SunMicroPDP.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }        
    }
    
    private static Document sign(Document doc) throws InstantiationException, IllegalAccessException, ClassNotFoundException,
            NoSuchAlgorithmException, InvalidAlgorithmParameterException, KeyException, MarshalException, XMLSignatureException,
            FileNotFoundException, TransformerException {

        String providerName = System.getProperty("jsr105Provider", "org.jcp.xml.dsig.internal.dom.XMLDSigRI");

        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");
        KeyInfoFactory kif = fac.getKeyInfoFactory();

        DigestMethod digestMethod = fac.newDigestMethod(DigestMethod.SHA256, null);
        Transform transform = fac.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null);
        Reference reference = fac.newReference("", digestMethod, Collections.singletonList(transform), null, null);
        SignatureMethod signatureMethod = fac.newSignatureMethod("http://www.w3.org/2001/04/xmldsig-more#rsa-sha256", null);
        CanonicalizationMethod canonicalizationMethod = fac.newCanonicalizationMethod(CanonicalizationMethod.EXCLUSIVE, (C14NMethodParameterSpec) null);

        // Create the SignedInfo
        SignedInfo si = fac.newSignedInfo(canonicalizationMethod, signatureMethod, Collections.singletonList(reference));


        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);

        KeyPair kp = kpg.generateKeyPair();

        KeyValue kv = kif.newKeyValue(kp.getPublic());

        // Create a KeyInfo and add the KeyValue to it
        KeyInfo ki = kif.newKeyInfo(Collections.singletonList(kv));
        DOMSignContext dsc = new DOMSignContext(kp.getPrivate(), doc.getDocumentElement());

        XMLSignature signature = fac.newXMLSignature(si, ki);
        signature.sign(dsc);

//        TransformerFactory tf = TransformerFactory.newInstance();
//        Transformer trans = tf.newTransformer();
//
//        // output the resulting document
//        OutputStream os;
//
//        os = new FileOutputStream("xmlOut.xml");
//
//        trans.transform(new DOMSource(doc), new StreamResult(os));
        return doc;

    }


    
}
