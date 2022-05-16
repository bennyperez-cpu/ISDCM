/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isdcm.accesscontrol;

import java.io.BufferedReader;
import java.io.IOException;
import static java.lang.System.exit;

import isdcm.accesscontrol.Balana_xacml.InterBalana_xacml;
import isdcm.accesscontrol.Sun_xacml.InterSun_xacml;
import isdcm.accesscontrol.Signature_xml.InterSignature_xml;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.System.exit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author victor
 */
public class Interface {

    private BufferedReader buff_reader;
    private static String[] options = {
        "[1] Autorización XACML con Sun",
        "[2] Autorización XACML con Balana",
        "[3] Firma Políticas XACML",
        "[4] Salir"
    };
    
    public Interface (BufferedReader reader) {
        this.buff_reader = reader;
    }
    
    public void MainInterface() throws Exception {
        System.out.println("MENU PRINCIPAL.");
        optionsMainInterface();
    }

    public void optionsMainInterface() throws Exception {
        try {
            while(true) {
                System.out.println("Seleccione cualquiera de las siguientes opciones:");
                for (String s : options)
                    System.out.println(s);
                String option = buff_reader.readLine();
                switch (option){
                    case "1":
                        InterSun_xacml interf_1 = new InterSun_xacml();
                        interf_1.mainSun();
                        break;
                    case "2":
                        InterBalana_xacml interf_2 = new InterBalana_xacml();
                        interf_2.mainBalana();
                        break;
                        
                    case "3":
                        InterSignature_xml signature = new InterSignature_xml();
                        signature.mainSig();
                        break;
                        
                    case "4":
                        exit(0);
                        break;
                    default:
                        System.out.println("La opción seleccionada no es correcta");
                        optionsMainInterface();
                }
            }
        } catch (IOException ex) {
            System.out.println("Ocurrió un error durante la ejecución");
            optionsMainInterface();
        }
    }    
}
