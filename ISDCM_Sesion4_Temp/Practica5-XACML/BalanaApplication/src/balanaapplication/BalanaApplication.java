/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balanaapplication;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
/**
 *
 * @author alber
 */
public class BalanaApplication {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String requestFile = getRequestFileFromUserInput();
        if(requestFile.isEmpty()){
            System.out.println("No request file selected. Ending.");
            System.exit(1);
        }
        ArrayList<String> selectedPolicyFiles = getPolicyFilesFromUserInput();
        if(selectedPolicyFiles.isEmpty()){
            System.out.println("No policies selected. Ending.");
            System.exit(2);
        }
        else{
            System.out.println(requestFile);
            System.out.println(selectedPolicyFiles.toString());
        }
        
        BalanaHelper balanaHelper = new BalanaHelper();
        balanaHelper.initBalana(selectedPolicyFiles);
        
        long time = System.nanoTime();
        String response = balanaHelper.executeRequest(requestFile);
        long time2 = System.nanoTime();
        System.out.println("Execution time was of: " + String.valueOf((time2-time)/1000000) + "ms");
        
        // Print and save the result
        System.out.println("\n======================== XACML Response ===================");
        System.out.println(response);
        System.out.println("\n Please note that the decision is: " + (response.contains("<Decision>Deny")?"Deny":"Permit"));
        System.out.println("===========================================================");
        
        // Create dir if not exists and sava file
        File f = new File((new File(".")).getCanonicalPath() + File.separator + "src" + File.separator + "resources" + File.separator +
                    "support-xacml-3-0" + File.separator + "support" + File.separator + "response");
        if (!f.exists()) Files.createDirectories(f.toPath());
        Files.write(Paths.get(f.toPath() + File.separator + "XACMLContextResponse.xml"), 
                    response.getBytes());
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
        return "XACMLRequest"+requestNum+".xml";
    }
    private static ArrayList<String> getPolicyFilesFromUserInput() {
        // Ask for policies
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Please select which policies you want to be applied:");
        System.out.println("\tformat: one by one separated by commas and only the number");
        System.out.println("\t(otherwise it will be ignored); i.e: 1,3 or 1,2,3");        
        System.out.println("1. PlayMaverickYear: Everyone can play Maverick until 2015");
        System.out.println("2. ThreeCopiesBaztan: Premiums can copy Baztan up to 3 times");
        System.out.println("3. PlayDocumentalPlanetsUK: Every UK citizen can play");
        Scanner keyboard = new Scanner(System.in);
        ArrayList<String> policyFiles = new ArrayList<>();
        Set<String> selected = new HashSet<>(Arrays.asList(keyboard.nextLine().replaceAll(" ","").trim().split(",")));
        for (int i = 1; i <= 3; ++i){
            if (selected.contains(Integer.toString(i))){
                policyFiles.add("XACMLPolicy"+i+".xml");
            }
        }
        return policyFiles;
    }
}
