/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isdcm.accesscontrol.Sun_xacml;

import com.sun.xacml.Indenter;
import com.sun.xacml.ctx.Result;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author victor
 */
public class Show_Result {
    public static void print(OutputStream output, Indenter indenter, Set results) {

        PrintStream out = new PrintStream(output);
        String indent = indenter.makeString();
        out.println(indent + "<Response>");
        Iterator it = results.iterator();
        indenter.in();
        while (it.hasNext()) {
            Result result = (Result)(it.next());
            result.encode(out, indenter);
        }
        indenter.out();
        out.println(indent + "</Response>");
    }    
}
