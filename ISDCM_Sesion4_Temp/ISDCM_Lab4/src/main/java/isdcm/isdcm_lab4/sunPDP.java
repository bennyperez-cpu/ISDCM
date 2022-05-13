package isdcm.isdcm_lab4;

import com.sun.xacml.PDP;
import com.sun.xacml.PDPConfig;
import com.sun.xacml.ParsingException;
import com.sun.xacml.ctx.RequestCtx;
import com.sun.xacml.ctx.ResponseCtx;
import com.sun.xacml.finder.AttributeFinder;
import com.sun.xacml.finder.PolicyFinder;
import com.sun.xacml.finder.impl.CurrentEnvModule;
import com.sun.xacml.finder.impl.FilePolicyModule;
import com.sun.xacml.finder.impl.SelectorModule;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import tools.Document.DocumentHelper;
import tools.Security.*;
import org.w3c.dom.Document;

public class sunPDP {

    private PDP pdp = null;

    public sunPDP(ArrayList<String> policyFiles) throws Exception {
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
            String PATH_TO_POLICY_SET_FILE = "src/resources/support-xacml-2-0.support/policy/XACMLPolicySet.xml";
            File f = new File(PATH_TO_POLICY_SET_FILE);
            if (f.exists())   Files.walk(f.toPath()).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
            Files.createFile(f.toPath());

            //Header
            String header = "<PolicySet xmlns=\"urn:oasis:names:tc:xacml:1.0:core:schema:wd-17\"  PolicyCombiningAlgId=\"urn:oasis:names:tc:xacml:1.0:policy-combining-algorithm:deny-overrides\" PolicySetId=\"PolicySetExample\" Version=\"1.0\">\n" +
                    "    <Target>\n" +
                    "        <Subject>\"Role\"</Subject>\n" +
                    "    </Target>\n";
            Files.write(f.toPath(), header.getBytes());

            String signedPolicyFilename = "src/resources/support-xacml-2-0.support/policy/signedPolicy.xml";
            //Policies
            for (int i = 0; i < selectedPolicyFiles.size(); ++i){
                //Load unsigned policy into a DOM document
                Document policyDoc = DocumentHelper.loadDocumentFromFile(selectedPolicyFiles.get(i));
                //Sign that policy document
                SecurityHelper.signDocument(policyDoc);
                //Store signed policy document as signedPolicy.xml
                DocumentHelper.writeDocumentToFile(policyDoc,signedPolicyFilename);

                //Load signed policy into DOM document (signedPolicy.xml)
                DocumentHelper.loadDocumentFromFile(signedPolicyFilename);
                //validate policy
                boolean isValid = SecurityHelper.validateDocument(policyDoc);
                if(isValid){
                    byte[] policyBytes = DocumentHelper.getPolicyBytes(policyDoc);
                    Files.write(f.toPath(),policyBytes,StandardOpenOption.APPEND);
                    Files.write(f.toPath(),"\n".getBytes(),StandardOpenOption.APPEND);
                }
            }

            //End
            String end = "</PolicySet>";
            Files.write(f.toPath(),end.getBytes(),StandardOpenOption.APPEND);

            return PATH_TO_POLICY_SET_FILE;
        } catch (IOException ex) {
            Logger.getLogger(sunPDP.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
