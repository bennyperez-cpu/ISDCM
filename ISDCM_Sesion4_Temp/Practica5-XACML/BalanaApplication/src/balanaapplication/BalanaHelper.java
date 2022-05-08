/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balanaapplication;

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

/**
 *
 * @author alber
 */
public class BalanaHelper {
    private Balana balana;
    private static String PATH_TO_CONFIG_FILE;
    private static String PATH_TO_REQUESTS;
    private static String PATH_TO_POLICIES;
    private static String PATH_TO_SELECTED_POLICIES;
    private static final String FOLDER_POLICIES_NAME = "SelectedPolicies";
    
    public BalanaHelper(){
        try {
            PATH_TO_CONFIG_FILE = (new File(".")).getCanonicalPath() + File.separator + "src" + File.separator + "resources" + File.separator +
                    "support-xacml-3-0" + File.separator + "support" + File.separator + "config" + File.separator + "config_rbac.xml";
            PATH_TO_REQUESTS = (new File(".")).getCanonicalPath() + File.separator + "src" + File.separator + "resources" + File.separator +
                    "support-xacml-3-0" + File.separator + "support" + File.separator + "requests" + File.separator;
            PATH_TO_POLICIES = (new File(".")).getCanonicalPath() + File.separator + "src" + File.separator + "resources" + File.separator + 
                    "support-xacml-3-0" + File.separator + "support" + File.separator + "policy" + File.separator;
            PATH_TO_SELECTED_POLICIES = PATH_TO_POLICIES + FOLDER_POLICIES_NAME + File.separator;
        } catch (IOException ex) {
            Logger.getLogger(BalanaHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    };

    public void initBalana(ArrayList<String> selectedPolicyFiles){
        // set first the selected policy files
        setPolicyFiles(selectedPolicyFiles);
        
        // load config and policies from files
        System.setProperty(FileBasedPolicyFinderModule.POLICY_DIR_PROPERTY, PATH_TO_SELECTED_POLICIES);
        System.setProperty(ConfigurationStore.PDP_CONFIG_PROPERTY, PATH_TO_CONFIG_FILE);     
        
        // create instance of Balana
        balana = Balana.getInstance();
    }
    
    public String executeRequest(String requestFile){
        try {
            String request = new String(Files.readAllBytes(Paths.get(PATH_TO_REQUESTS + requestFile)));
            PDP pdp = new PDP(balana.getPdpConfig());
            
            String response = pdp.evaluate(request);
            return response;
            
        } catch (IOException ex) {
            Logger.getLogger(BalanaHelper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    // Copy the selected policies to a new folder so Balana can load that folder with the selected policies 
    private void setPolicyFiles(ArrayList<String> selectedPolicyFiles){
        try {
            File f = new File(PATH_TO_SELECTED_POLICIES);
            if (f.exists())   Files.walk(f.toPath()).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
            Files.createDirectories(f.toPath());
            
            for (int i = 0; i < selectedPolicyFiles.size(); ++i){
                String policy = selectedPolicyFiles.get(i);
                
                File opolicy = new File(PATH_TO_POLICIES + policy);
                File fpolicy = new File(PATH_TO_SELECTED_POLICIES + policy);
                Files.copy(opolicy.toPath(), fpolicy.toPath());
            }
        } catch (IOException ex) {
            Logger.getLogger(BalanaHelper.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
}
