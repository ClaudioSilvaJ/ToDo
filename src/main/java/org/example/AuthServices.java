package org.example;
import org.example.classes.LoginDTO;
import org.mongodb.morphia.Datastore;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
@Path("/auth")
public class AuthServices {
    Datastore datastore = new MorphiaConfig().getDatastore();

    @POST
    @Path("/login")
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    public Response login(final LoginDTO dto) {
        if (dto == null) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Please enter your login details!").build();
        }
        if(!datastore.createQuery(LoginDTO.class).field("email").equal(dto.getEmail()).field("password").equal(dto.getPassword()).asList().isEmpty()) {
            return Response.ok("{\"message\":\"Login successful\"}").build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid login").build();
        }

    }

}
