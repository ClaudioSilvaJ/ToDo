package org.example.api.services;
import org.example.APIServer;
import org.example.dtos.TaskDTO;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/user")
public class TaskServices extends APIServer {
    @POST
    @Path("/task")
    @Consumes("application/json; charset=UTF-8")
    public Response createTask(TaskDTO newTask){
        try {
            datastore.save(newTask);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @PUT
    @Path("/task")
    @Consumes("application/json; charset=UTF-8")
    public Response updateTask(TaskDTO newDatesTask){
        try {
            Query<TaskDTO> query =
                    datastore.createQuery(TaskDTO.class)
                    .field("name")
                    .equal(newDatesTask.getName());
            TaskDTO task = query.get();
            if(task.getName() == null) return Response.status(Response.Status.BAD_REQUEST).build();
            UpdateOperations<TaskDTO> upd = datastore.createUpdateOperations(TaskDTO.class);
            upd.set("state", newDatesTask.getState());
            upd.set("obs", newDatesTask.getObs());

            UpdateResults results = datastore.update(query, upd);

            if (results.getUpdatedCount() > 0) {
                return Response.status(Response.Status.OK).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }

        } catch (Exception e){
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }
    @DELETE
    @Path("/task")
    @Consumes("application/json; charset=UTF")
    public Response deleteTask(TaskDTO task){
        TaskDTO taskDeleted = datastore.findAndDelete(datastore.createQuery(TaskDTO.class).field("name").equal(task.getName()));
        if(taskDeleted != null) return Response.status(Response.Status.OK).build();
        return Response.status(Response.Status.BAD_GATEWAY).build();
    }

    @GET
    @Path ("/task")
    @Consumes("application/json; charset=UTF")
    @Produces("application/json; charset=UTF")
    public Response getTask (String owner){
        System.out.println("Entrou no get");
        System.out.println(owner);
        List<TaskDTO> tasks = new ArrayList<>(datastore.createQuery(TaskDTO.class)
                .field("owner").equal("admin")
                .asList());
        if(!tasks.isEmpty()) return Response.status(Response.Status.OK).entity(tasks).build();
        return Response.status(Response.Status.BAD_REQUEST).entity(tasks).build();

    }
}
