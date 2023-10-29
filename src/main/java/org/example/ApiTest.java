package org.example;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
@Path("/")
public class ApiTest {

    @GET
    @Path("/test")
    public String test(){
        return "API WORKS!";
    }
}
