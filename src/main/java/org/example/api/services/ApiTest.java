package org.example.api.services;
import javax.ws.rs.*;
@Path("/")
public class ApiTest {

    @GET
    @Path("/test")
    public String test(){
        return "API WORKS!";
    }
}
