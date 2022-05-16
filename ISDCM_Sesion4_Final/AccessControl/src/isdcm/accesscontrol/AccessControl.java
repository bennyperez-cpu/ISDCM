/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isdcm.accesscontrol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import isdcm.accesscontrol.Interface;

/**
 *
 * @author victor
 */
public class AccessControl {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        System.out.println("ENTREGA 4 - CONTROL DE ACCESO A CONTENIDOS DIGITALES");
        System.out.println("INTEGRANTES: Victor Carhuaricra - Benny Perez");
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader buff_reader = new BufferedReader(inputStreamReader);
        Interface mainInt = new Interface(buff_reader);
        mainInt.MainInterface();
    }
    
}
