/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isdcm.accesscontrol.Sun_xacml;

import com.sun.xacml.Indenter;
import com.sun.xacml.ParsingException;
import com.sun.xacml.UnknownIdentifierException;
import com.sun.xacml.ctx.ResponseCtx;
import java.io.BufferedReader;
import java.io.IOException;
import isdcm.accesscontrol.Interface;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Scanner;

/**
 *
 * @author victor
 */
public class InterSun_xacml {
    
    private static final String PolicyFile = "src/xacml2_resources/policy/XACMLPolicy";
    private static final String RequestFile = "src/xacml2_resources/request/XACMLRequest";
   
    
    private static final String SaveFile = "src/resources/outputs/XACMLContextResponse";
    
    
       
    public void mainSun() throws Exception{
    Scanner reader = new Scanner(System.in);
        System.out.println("Digite un numero [1 : 3] para seleccionar la Policy");
        int policy_num = reader.nextInt();
        
        System.out.println("Digite un numero [1 : 5] para seleccionar la Request");
        int request_num = reader.nextInt();
        
        String[] pathPolicies;        
        pathPolicies = new String[1];
        pathPolicies[0] = PolicyFile + policy_num + ".xml";
        System.out.println("Leyendo Policy: " + pathPolicies[0]);
            
        Sun_PDP simplePDP = new Sun_PDP(pathPolicies);

        String requestFile = RequestFile + request_num + ".xml";
        System.out.println("Leyendo RequestFile: " + requestFile);
        
        long initialTime = System.nanoTime(); 
        ResponseCtx response = simplePDP.evaluate(requestFile);
        long endTime = System.nanoTime();
        System.out.println("Tiempo de ejecución: " + String.valueOf((endTime-initialTime)/1000000) + "ms");
        
        // Mostrar el resultado en consola
        Show_Result.print(System.out, new Indenter(), response.getResults());
        
        // Guardar el resultado
        String pathToSave = SaveFile + "Policy" + policy_num + "Request" + request_num;
        System.out.println("Escribiendo resultado en: " + pathToSave);
        OutputStream outputStream = new FileOutputStream(pathToSave);
        Show_Result.print(outputStream, new Indenter(), response.getResults());
    }    
}
