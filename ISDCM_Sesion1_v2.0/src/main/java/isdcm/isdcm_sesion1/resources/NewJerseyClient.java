/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isdcm.isdcm_sesion1.resources;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:ServerREST [javaee8]<br>
 * USAGE:
 * <pre>
 *        NewJerseyClient client = new NewJerseyClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author alumne
 */
public class NewJerseyClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/ISDCM_Sesion2/resources";

    public NewJerseyClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("javaee8");
    }

    public <T> T lista_video(Class<T> responseType, String enlace) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (enlace != null) {
            resource = resource.queryParam("enlace", enlace);
        }
        resource = resource.path("getVideos");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
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
