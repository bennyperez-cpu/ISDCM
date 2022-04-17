package isdcm.isdcm_sesion1.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author 
 */
@Path("javaee8")
public class ClientREST {
    
    @GET
    public Response ping(){
        return Response
                .ok("ping")
                .build();
    }
}
