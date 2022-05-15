/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isdcm.accesscontrol.Balana_xacml;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

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
import org.wso2.balana.Indenter;
import org.wso2.balana.PDP;
import org.wso2.balana.PDPConfig;
import org.wso2.balana.ctx.ResponseCtx;
import org.wso2.balana.ctx.xacml2.Result;
import org.wso2.balana.finder.impl.FileBasedPolicyFinderModule;
import org.wso2.balana.utils.Utils;


/**
 *
 * @author victor
 */
public class InterBalana_xacml {
    
    static Balana balana;
    
    private static final String PolicyFile = "/home/victor/Documents/ISDCM/Entrega 4/App_Entrega_4/AccessControl/src/xacml3_resources/policy/XACMLPolicy";
    private static final String RequestFile = "/home/victor/Documents/ISDCM/Entrega 4/App_Entrega_4/AccessControl/src/xacml3_resources/request/XACMLRequest";
    
    //private static final String pathToSaveFile = "/home/victor/Documents/ISDCM/Entrega 4/App_Entrega_4/AccessControl/src/resources/outputs/XACMLContextResponse";    
    
    public void mainBalana() throws Exception{
    Scanner reader = new Scanner(System.in);
        System.out.println("Digite un numero [1 : 3] para seleccionar la Policy,la número 4 corresponde al ejercicio 6");
        int policyNumber = reader.nextInt();
        
        System.out.println("Digite un numero [1 : 5] para seleccionar la Request,los números 6 y 7 corresponden al ejercicio 6");
        int requestNumber = reader.nextInt();

        String pathPolicies = PolicyFile + policyNumber + ".xml";
        System.out.println("Leyendo Policy: " + pathPolicies);
        
        String requestFile = RequestFile + requestNumber + ".xml";
        System.out.println("Leyendo RequestFile: " + requestFile);
        
        Launch_Balana(pathPolicies);

        PDPConfig pdpConfig = balana.getPdpConfig();
        PDP pdp = new PDP(new PDPConfig(pdpConfig.getAttributeFinder(), pdpConfig.getPolicyFinder(), null, true));

        String response = pdp.evaluate(getXacmlResponse(requestFile));
        System.out.println(response);

	}
	
    private static void Launch_Balana(String pathPolicies) {

        System.setProperty(FileBasedPolicyFinderModule.POLICY_DIR_PROPERTY, pathPolicies);
        balana = Balana.getInstance();
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
