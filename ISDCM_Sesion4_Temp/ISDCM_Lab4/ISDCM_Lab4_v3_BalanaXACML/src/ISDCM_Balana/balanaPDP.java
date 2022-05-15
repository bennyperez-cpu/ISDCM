package ISDCM_Balana;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.wso2.balana.Balana;
import org.wso2.balana.ConfigurationStore;
import org.wso2.balana.PDP;
import org.wso2.balana.finder.impl.FileBasedPolicyFinderModule;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alumne
 */
public class balanaPDP {
    
    public static Balana balana;
    private static String routeConfig = "src/resources/support-xacml-3-0/support/config/config_rbac.xml";
    private static String routeRequests = "src/resources/support-xacml-3-0/support/requests/";
    private static String routePolicies = "src/resources/support-xacml-3-0/support/policy/";
    private static String routeSelectedPolicies = "src/resources/support-xacml-3-0/support/policy/SelectedPolicies/";

    public void initBalana(ArrayList<String> filePolicies) {
        //https://github.com/wso2/balana/blob/master/modules/balana-samples/custom-combining-algo/src/main/java/org/wso2/balana/samples/custom/algo/Main.java
        
        setPolicyFiles(filePolicies);
        
        System.setProperty(FileBasedPolicyFinderModule.POLICY_DIR_PROPERTY, routeSelectedPolicies);
        System.setProperty(ConfigurationStore.PDP_CONFIG_PROPERTY, routeConfig);
        
        balana = Balana.getInstance();
            
    }
    
    
    public String executeRequest(String requestFile){
        try {
            String request = new String(Files.readAllBytes(Paths.get(routeRequests + requestFile)));
            PDP pdp = new PDP(balana.getPdpConfig());
            
            String response = pdp.evaluate(request);
            return response;
            
        } catch (IOException ex) {
            Logger.getLogger(balanaPDP.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    // Copy the selected policies to a new folder so Balana can load that folder with the selected policies 
    public void setPolicyFiles(ArrayList<String> selectedPolicyFiles){
        try {
            File f = new File(routeSelectedPolicies);
            if (f.exists())   Files.walk(f.toPath()).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
            Files.createDirectories(f.toPath());
            
            for (int i = 0; i < selectedPolicyFiles.size(); ++i){
                String policy = selectedPolicyFiles.get(i);
                System.out.println(policy);
                File opolicy = new File(routePolicies + policy);
                System.out.println(routePolicies + policy);
                
                System.out.println("-------------------------------------------------------------");
                File fpolicy = new File(routeSelectedPolicies + policy);
                Files.copy(opolicy.toPath(), fpolicy.toPath());
            }
        } catch (IOException ex) {
            Logger.getLogger(balanaPDP.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
}
