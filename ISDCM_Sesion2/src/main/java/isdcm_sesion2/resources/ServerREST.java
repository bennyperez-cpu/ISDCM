package isdcm_sesion2.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import tools.videoDAO;

/**
 *
 * @author 
 */
@Path("javaee8")
public class ServerREST {
    
    /**
     * Sample of GET method
     * @param info
     * @param fecha
     * @return 
     */

    public ServerREST() {
    }
     
    @Path("getInfo")
    @GET    
    @Produces("text/html")
    public String getReproducciones(@QueryParam("enlace") String enlace) {
        int visualizations = videoDAO.getReproducciones(enlace);
        return Integer.toString(visualizations);
        //@QueryParam("enlace") String enlace
    
    }

    /**
     * Sample of POST method
     * 
     * @param info
     * @param fecha
     * @return 
     */
    @Path("postInfo")   
    @POST    
    @Consumes("application/x-www-form-urlencoded")
    @Produces("text/html")
    public String increReproducciones(@FormParam("enlace") String enlace) 
    {                
        boolean success = videoDAO.incrementReproducciones(enlace);
        return Boolean.toString(success);
        //  @FormParam("enlace") String enlace
    }    
    
}

