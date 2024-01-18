package com.todo.services;

import com.todo.util.APIBaseTest;
import org.example.dtos.LoginDTO;
import org.example.dtos.TaskDTO;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.ws.rs.core.Response;

import java.util.List;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.JVM)
public class TaskServicesTest extends APIBaseTest {
    private final String dummyEmail = "test@example.com";
    private final String dummyPassword = "password123";

    @Test
    public void createTask() {
        LoginDTO User = createUser(dummyEmail, dummyPassword);
        TaskDTO taskToCreate = createTask("Name Task test 1", TaskDTO.State.COMPLETED, "Obs Task test 1", User.getEmail());
        assertTaskCreation(taskToCreate, User.getEmail());
    }

    @Test
    public void updateTask() {
        LoginDTO User = createUser(dummyEmail, dummyPassword);
        TaskDTO taskToUpdate = createTask("Name Task test 2", TaskDTO.State.INCOMPLETE, "Obs Task test 2", User.getEmail());

        Response responseCreateTask = taskServices.createTask(taskToUpdate);
        assertEquals(Response.Status.CREATED.getStatusCode(), responseCreateTask.getStatus());

        taskToUpdate.setState(TaskDTO.State.COMPLETED);
        taskToUpdate.setObs("Obs Task test 2 updated");
        Response responseUpdateTask = taskServices.updateTask(taskToUpdate);
        assertEquals(Response.Status.OK.getStatusCode(), responseUpdateTask.getStatus());

        assertTaskRetrievalAndComparison(taskToUpdate, User.getEmail());
        deleteTask(taskToUpdate);
    }

    @Test
    public void readTask() {
        LoginDTO User = createUser(dummyEmail, dummyPassword);
        TaskDTO taskToRead = createTask("Name Task test 3", TaskDTO.State.COMPLETED, "Obs Task test 3", User.getEmail());
        Response responseCreateTask = taskServices.createTask(taskToRead);
        assertEquals(Response.Status.CREATED.getStatusCode(), responseCreateTask.getStatus());

        String secondDummyEmail = "test2@example.com";
        String secondDummyPassword = "password456";
        LoginDTO User2 = createUser(secondDummyEmail, secondDummyPassword);
        TaskDTO taskToRead2 = createTask("Name Task test 4", TaskDTO.State.COMPLETED, "Obs Task test 4", User2.getEmail());
        Response responseCreateTask2 = taskServices.createTask(taskToRead2);
        assertEquals(Response.Status.CREATED.getStatusCode(), responseCreateTask2.getStatus());

        assertTaskRetrievalAndComparison(taskToRead, User.getEmail());
        deleteTask(taskToRead);
    }

    @Test
    public void deleteTask() {
        LoginDTO User = createUser(dummyEmail, dummyPassword);
        TaskDTO taskToDelete = createTask("Name Task test 5", TaskDTO.State.COMPLETED, "Obs Task test 5" , User.getEmail());
        Response responseCreateTask = taskServices.createTask(taskToDelete);
        assertEquals(Response.Status.CREATED.getStatusCode(), responseCreateTask.getStatus());
        Response responseDeleteTask = taskServices.deleteTask(taskToDelete);
        assertEquals(Response.Status.OK.getStatusCode(), responseDeleteTask.getStatus());
        assertTaskNotFound(taskToDelete, User.getEmail());
    }

    private TaskDTO createTask(String name, TaskDTO.State state, String obs, String owner) {
        TaskDTO task = new TaskDTO();
        task.setName(name);
        task.setState(state);
        task.setObs(obs);
        task.setOwner(owner);
        return task;
    }

    private void assertTaskCreation(TaskDTO taskToCreate, String ownerEmail) {
        Response responseCreateTask = taskServices.createTask(taskToCreate);
        assertEquals(Response.Status.CREATED.getStatusCode(), responseCreateTask.getStatus());
        assertTaskRetrievalAndComparison(taskToCreate, ownerEmail);
        deleteTask(taskToCreate);
    }

    private void assertTaskRetrievalAndComparison(TaskDTO taskToCompare, String ownerEmail) {
        List<TaskDTO> tasks = getTasks(ownerEmail);
        assertTaskListContains(tasks, taskToCompare);
    }

    private void assertTaskNotFound(TaskDTO taskToFind, String ownerEmail) {
        List<TaskDTO> tasks = getTasks(ownerEmail);
        assertEquals(0, tasks.stream().filter(task -> taskToFind.getName().equals(task.getName())).count());
    }

    @SuppressWarnings("unchecked")
    private List<TaskDTO> getTasks(String ownerEmail) {
        Response responseGetTasks = taskServices.getTask(ownerEmail);
        return (List<TaskDTO>) responseGetTasks.getEntity();
    }

    private void assertTaskListContains(List<TaskDTO> tasks, TaskDTO taskToCompare) {
        tasks.stream()
                .filter(task -> taskToCompare.getName().equals(task.getName()))
                .findFirst()
                .ifPresent(task -> {
                    assertEquals(taskToCompare.getName(), task.getName());
                    assertEquals(taskToCompare.getState(), task.getState());
                    assertEquals(taskToCompare.getObs(), task.getObs());
                });
    }

    private void deleteTask(TaskDTO taskToDelete) {
        Response responseDeleteTask = taskServices.deleteTask(taskToDelete);
        assertEquals(Response.Status.OK.getStatusCode(), responseDeleteTask.getStatus());
    }

    private LoginDTO createUser(String email, String password) {
        LoginDTO userToRegister = new LoginDTO();
        userToRegister.setEmail(email);
        userToRegister.setPassword(password);
        assertEquals(Response.Status.OK.getStatusCode(), authServices.register(userToRegister).getStatus());
        return userToRegister;
    }
}
