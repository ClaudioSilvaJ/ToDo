package com.todo.services;

import static org.junit.Assert.assertEquals;
import javax.ws.rs.core.Response;
import org.example.dtos.LoginDTO;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.JVM)
public class AuthServicesTest extends TodoMainTest {

    public String dummyEmail = "test@example.com";
    public String dummyPassword = "password123";


    @Test
    public void loginAndCurrentTest() {
        createUser(dummyEmail, dummyPassword);
        LoginDTO userToLogin = new LoginDTO();
        userToLogin.setEmail("test@example.com");
        userToLogin.setPassword("password123");
        Response loginResponse = authServices.login(userToLogin);
        assertEquals(Response.Status.OK.getStatusCode(), loginResponse.getStatus());

        String token = loginResponse.getEntity().toString();
        Response currentResponse = authServices.getCurrentUser(token);
        assertEquals(Response.Status.OK.getStatusCode(), currentResponse.getStatus());
        LoginDTO user = (LoginDTO) currentResponse.getEntity();
        assertEquals(user.getEmail(), userToLogin.getEmail());
    }

    public void createUser(String email, String password){
        LoginDTO userToRegister = new LoginDTO();
        userToRegister.setEmail(email);
        userToRegister.setPassword(password);
        assertEquals(Response.Status.OK.getStatusCode(), authServices.register(userToRegister).getStatus());
    }
}
