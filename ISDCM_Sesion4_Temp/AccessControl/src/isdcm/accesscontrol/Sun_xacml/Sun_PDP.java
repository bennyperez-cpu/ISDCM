/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isdcm.accesscontrol.Sun_xacml;

import com.sun.xacml.ConfigurationStore;
import com.sun.xacml.PDP;
import com.sun.xacml.PDPConfig;
import com.sun.xacml.ParsingException;
import com.sun.xacml.UnknownIdentifierException;
import com.sun.xacml.ctx.RequestCtx;
import com.sun.xacml.ctx.ResponseCtx;
import com.sun.xacml.finder.AttributeFinder;
import com.sun.xacml.finder.AttributeFinderModule;
import com.sun.xacml.finder.PolicyFinder;
import com.sun.xacml.finder.impl.CurrentEnvModule;
import com.sun.xacml.finder.impl.FilePolicyModule;
import com.sun.xacml.finder.impl.SelectorModule;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author victor
 */
public class Sun_PDP {

    private PDP pdp = null;

    public Sun_PDP(String [] policyFiles) {
        FilePolicyModule filePolicyModule = new FilePolicyModule();
        for (String policyFile : policyFiles) filePolicyModule.addPolicy(policyFile);

        PolicyFinder policyFinder = new PolicyFinder();
        Set<FilePolicyModule> policyModules = new HashSet<>();
        policyModules.add(filePolicyModule);
        policyFinder.setModules(policyModules);

        CurrentEnvModule envAttributeModule = new CurrentEnvModule();
        SelectorModule selectorAttributeModule = new SelectorModule();

        AttributeFinder attributeFinder = new AttributeFinder();
        List<AttributeFinderModule> attributeModules = new ArrayList<>();
        attributeModules.add(envAttributeModule);
        attributeModules.add(selectorAttributeModule);
        attributeFinder.setModules(attributeModules);

        this.pdp = new PDP(new PDPConfig(attributeFinder, policyFinder, null));
    }

    public ResponseCtx evaluate(String requestFile) throws IOException, ParsingException {
        RequestCtx request = RequestCtx.getInstance(new FileInputStream(requestFile));
        return pdp.evaluate(request);
    }    
}
