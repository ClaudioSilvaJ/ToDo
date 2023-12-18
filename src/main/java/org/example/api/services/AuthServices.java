package org.example.api.services;
import org.example.functions.PasswordHashing;
import org.example.configs.MorphiaConfig;
import org.example.dtos.LoginDTO;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

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
        try {
            Query<LoginDTO> query = datastore.createQuery(LoginDTO.class).field("email").equal(dto.getEmail());
            LoginDTO user = query.get();
            if(user == null){ return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid login").build();}

            if(PasswordHashing.checkPassword(dto.getPassword(), user.getSalt(), user.getPassword())){ return Response.ok("{\"message\":\"Login successful\"}").build(); }
            else { return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid login").build(); }

        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid login").build();
        }


    }

}
