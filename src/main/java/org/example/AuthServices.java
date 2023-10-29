package org.example;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
@Path("/auth")
public class AuthServices {

    @POST
    @Path("/login")
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    public Response login(final LoginDTO dto) {
        if (dto == null) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Please enter your login details!").build();
        }
        if (dto.email.equals("admin") && dto.password.equals("admin")) {
            return Response.ok("{\"message\":\"Login successful\"}").build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid login").build();
        }

    }
}
