package com.todo.services;

import com.todo.util.APIBaseTest;
import org.example.dtos.LoginDTO;
import org.example.dtos.TaskDTO;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.JVM)
public class TaskServicesTest extends APIBaseTest {
    public String dummyEmail = "test@example.com";
    public String dummyPassword = "password123";

    @Test
    public void createAndDeleteTaskTest() {
        createUser(dummyEmail, dummyPassword);
        TaskDTO taskToCreate = new TaskDTO();
        taskToCreate.setName("Name Task test 1");
        taskToCreate.setState(TaskDTO.State.COMPLETED);
        taskToCreate.setObs("Obs Task test 1");
        Response responseCreateTask = taskServices.createTask(taskToCreate);
        assertEquals(Response.Status.CREATED.getStatusCode(), responseCreateTask.getStatus());

        Response responseDeleteTask = taskServices.deleteTask(taskToCreate);
        assertEquals(Response.Status.OK.getStatusCode(), responseDeleteTask.getStatus());
    }


    public void createUser(String email, String password){
        LoginDTO userToRegister = new LoginDTO();
        userToRegister.setEmail(email);
        userToRegister.setPassword(password);
        assertEquals(Response.Status.OK.getStatusCode(), authServices.register(userToRegister).getStatus());
    }

}
