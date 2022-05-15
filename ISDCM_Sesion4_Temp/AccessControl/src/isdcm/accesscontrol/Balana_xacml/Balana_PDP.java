/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isdcm.accesscontrol.Balana_xacml;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.wso2.balana.Balana;
import org.wso2.balana.PDP;
import org.wso2.balana.PDPConfig;
import org.wso2.balana.ParsingException;
import org.wso2.balana.ctx.ResponseCtx;
import org.wso2.balana.finder.AttributeFinder;
import org.wso2.balana.finder.PolicyFinder;
import org.wso2.balana.finder.PolicyFinderModule;
import org.wso2.balana.finder.impl.CurrentEnvModule;
import org.wso2.balana.finder.impl.FileBasedPolicyFinderModule;
import org.wso2.balana.finder.impl.SelectorModule;

/**
 *
 * @author victor
 */
public class Balana_PDP {
    
    private PDP pdp = null;
    
    static Balana balana;
    
    public Balana_PDP (String pathPolicies){
        System.setProperty(FileBasedPolicyFinderModule.POLICY_DIR_PROPERTY, pathPolicies);
        balana = Balana.getInstance();
        PDPConfig pdpConfig = balana.getPdpConfig();
	this.pdp = new PDP(new PDPConfig(pdpConfig.getAttributeFinder(), pdpConfig.getPolicyFinder(), null, true));
    }
    
    public static String getXacmlResponse(String path) throws Exception {
           TransformerFactory tf = TransformerFactory.newInstance();
           Transformer transformer = tf.newTransformer();
           transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
           StringWriter writer = new StringWriter();
           transformer.transform(new DOMSource(getFile(path)), new StreamResult(writer));

           return writer.getBuffer().toString().replaceAll("\n|\r", "");
       }
    
    private static Document getFile(String xmlFile) throws Exception {
         DocumentBuilderFactory builder = DocumentBuilderFactory.newInstance();
         DocumentBuilder docBuilder = builder.newDocumentBuilder();
        return docBuilder.parse(xmlFile);
     }    

}
