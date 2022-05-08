/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.sun.xacml.Indenter;
import com.sun.xacml.ctx.ResponseCtx;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.io.FileOutputStream;
import main.helpers.Document.DocumentHelper;

/**
 *
 * @author nfran
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        String requestFile = getRequestFileFromUserInput();
        if(requestFile.isEmpty()){
            System.out.println("No request file selected. Ending.");
            System.exit(1);
        }
        ArrayList<String> policyFiles = getPolicyFilesFromUserInput();
        if(policyFiles.isEmpty()){
            System.out.println("No policies selected. Ending.");
            System.exit(2);
        }
        else{
            System.out.println("Request File: "+requestFile);
            System.out.println("Policy Files: "+policyFiles.toString());
            SunPDP sunPDP = new SunPDP(policyFiles);
            // evaluate the request
            
            long time = System.nanoTime();
            ResponseCtx response = sunPDP.evaluate(requestFile);
            long time2 = System.nanoTime();
            System.out.println("Execution time was of: " + String.valueOf((time2-time)/1000000) + "ms");
        
            // Write response to file
            FileOutputStream fos = new FileOutputStream("src/resources/support-xacml-2-0/support/response/contextResponse.xml");
            response.encode(fos, new Indenter());
            // Print response
            System.out.println("\n======================== XACML Response ===================");
            response.encode(System.out, new Indenter());
            System.out.println("===========================================================");
        }
    }
    
    private static String getRequestFileFromUserInput() {
        // Ask for requests
        System.out.println("Please select which request you want to ask for:");
        System.out.println("1. bobRay / urn:mvideo:Mavericksv.mp4 / play / 2013-03-21");
        System.out.println("2. bobRay - noPremium - 1 / urn:mvideo:Baztan.mp4 / copy");
        System.out.println("3. bobRay / urn:bbc:mdocum:Planets.mp4 / playDocumental / Reino Unido");
        System.out.println("4. leoVergara / urn:bbc:mdocum:Planets.mp4 / playDocumental / Italia");
        System.out.println("5. leoVergara - Premium - 1 / urn:mvideo:Baztan.mp4 / copy");
        Scanner keyboard = new Scanner(System.in);
        int requestNum = keyboard.nextInt();
        if(requestNum<1 || requestNum >5){
            return "";
        }
        return "src/resources/support-xacml-2-0/support/requests/XACMLRequest"+requestNum+".xml";
    }

    private static ArrayList<String> getPolicyFilesFromUserInput() {
        // Ask for policies
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Please select which policies you want to be applied:");
        System.out.println("\tformat: one by one separated by commas and only the number");
        System.out.println("\t(otherwise it will be ignored); i.e: 1,3 or 1,2,3");        
        System.out.println("1. PlayMaverickYear");
        System.out.println("2. ThreeCopiesBaztan");
        System.out.println("3. PlayDocumentalPlanetsUK");
        Scanner keyboard = new Scanner(System.in);
        ArrayList<String> policyFiles = new ArrayList<String>();
        Set<String> selected = new HashSet<>(Arrays.asList(keyboard.nextLine().replaceAll(" ","").trim().split(",")));
        for (int i = 1; i <= 3; ++i){
            if (selected.contains(Integer.toString(i))){
                policyFiles.add("src/resources/support-xacml-2-0/support/policy/XACMLPolicy"+i+".xml");
            }
        }
        return policyFiles;
    }
}
