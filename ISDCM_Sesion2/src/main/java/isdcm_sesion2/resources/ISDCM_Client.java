/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isdcm_sesion2.resources;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:ServerREST [javaee8]<br>
 * USAGE:
 * <pre>
 *        ISDCM_Client client = new ISDCM_Client();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author alumne
 */
public class ISDCM_Client {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/ISDCM_Sesion2/resources";

    public ISDCM_Client() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("javaee8");
    }

    public String increReproducciones() throws ClientErrorException {
        return webTarget.path("postInfo").request(javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED).post(null, String.class);
    }

    public String getReproducciones(String enlace) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (enlace != null) {
            resource = resource.queryParam("enlace", enlace);
        }
        resource = resource.path("getInfo");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_HTML).get(String.class);
    }

    public void close() {
        client.close();
    }
    
}
