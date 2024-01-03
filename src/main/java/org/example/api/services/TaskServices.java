package org.example.api.services;
import org.example.dtos.TaskDTO;
import org.example.dtos.TokenDTO;
import org.example.functions.PasswordHashing;
import org.example.configs.MorphiaConfig;
import org.example.dtos.LoginDTO;
import org.example.configs.TokenGenerator;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/task")
public class TaskServices {
    Datastore datastore = new MorphiaConfig().getDatastore();
    @POST //Todo - Create task
    @Path("/create")
    @Consumes("application/json; charset=UTF-8")
    public Response CreateTask(TaskDTO newTask){
        datastore.save(newTask);
        return Response.status(Response.Status.CREATED).build();
    }



//    @PUT //Todo - Edit task
//    @DELETE //Todo - Delete task
//    @GET //Todo - Get tasks
}
