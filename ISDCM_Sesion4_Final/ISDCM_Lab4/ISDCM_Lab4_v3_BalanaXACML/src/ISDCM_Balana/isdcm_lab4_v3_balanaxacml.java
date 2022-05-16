package ISDCM_Balana;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import org.wso2.balana.Balana;
import org.wso2.balana.ctx.ResponseCtx;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alumne
 */
public class isdcm_lab4_v3_balanaxacml {

    /**
     * @param args the command line arguments
     */
public static void main(String[] args) throws Exception {

        String fileReceived = requestFileUser();
        if(fileReceived.isEmpty()){
            System.err.println("No se recibió el archivo de solicitud");       
        }
        ArrayList<String> filePolicies = requestFilePolicies();
        if(filePolicies.isEmpty()){
            System.err.println("No se recibió el archivo de políticas");
        }
        
        if (!fileReceived.isEmpty() && !filePolicies.isEmpty()){
            System.out.println("\n======================== XACML Request ====================");
            System.out.println("Archivo de Solicitud: "+fileReceived);
            System.out.println("===========================================================");
            
            System.out.println("\n======================== XACML Request ====================");
            System.out.println("Archivo de Políticas: "+filePolicies.toString());
            System.out.println("===========================================================");
            
            balanaPDP balanaPdP = new balanaPDP();
            balanaPdP.initBalana(filePolicies); 
            
            
            long initialTime = System.nanoTime();
            String evaluationResp = balanaPdP.executeRequest(fileReceived);
            long endTime = System.nanoTime();
            System.out.println("Tiempo de ejecución: " + String.valueOf((endTime-initialTime)/1000000) + "ms");
        
            OutputStream outputStream = new FileOutputStream("src/resources/support-xacml-3-0/support/response/contextResponse.xml");
         
            System.out.println("\n======================== XACML Response ===================");
            System.out.println(evaluationResp);
            System.out.println("===========================================================");
        
        }
    }
    
    public static String requestFileUser() {
    
        System.out.println("Selecciona tu solicitud de la lista:");
        System.out.println("1. bobRay - urn:mvideo:Mavericksv.mp4 - play - 2013-03-21");
        System.out.println("2. bobRay - noPremium - 1 - urn:mvideo:Baztan.mp4 - copy");
        System.out.println("3. bobRay - urn:bbc:mdocum:Planets.mp4 - playDocumental - Reino Unido");
        System.out.println("4. leoVergara - urn:bbc:mdocum:Planets.mp4 - playDocumental - Italia");
        System.out.println("5. leoVergara - Premium - 1 - urn:mvideo:Baztan.mp4 - copy");
        Scanner keyNumber = new Scanner(System.in);
        int keyNumberInt = keyNumber.nextInt();
        if(keyNumberInt >5){
            System.out.println("Número fuera de rango");
            return "";
        }
        return "XACMLRequest"+keyNumberInt+".xml";
    }

    public static ArrayList<String> requestFilePolicies() {
        
        ArrayList<String> policyFiles = new ArrayList<String>();
        System.out.println("Selecciona la política que deseas ejecutar:");
        System.out.println("puedes seleccionar más de una política, separarlo con comas, por ejemplo 1,2; 1,3 ");       
        System.out.println("1.- PlayMaverickYear");
        System.out.println("2.- ThreeCopiesBaztan");
        System.out.println("3.- PlayDocumentalPlanetsUK");
        Scanner keyboard = new Scanner(System.in);
        Set<String> selectedPolicies = new HashSet<>(Arrays.asList(keyboard.nextLine().split(",")));
        for (int i = 1; i <= 3; ++i){
            if (selectedPolicies.contains(Integer.toString(i))){
                policyFiles.add("XACMLPolicy"+i+".xml");
            }
        }
        return policyFiles;
    }
    
}
