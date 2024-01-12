package com.todo.services;

import static org.junit.Assert.assertEquals;
import javax.ws.rs.core.Response;
import com.todo.util.APIBaseTest;
import org.example.dtos.LoginDTO;
import org.example.dtos.TokenDTO;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.JVM)
public class AuthServicesTest extends APIBaseTest {

    public String dummyEmail = "test@example.com";
    public String dummyPassword = "password123";


    @Test
    public void createUser(){
        LoginDTO User = createUser(dummyEmail, dummyPassword);
        assertEquals(User.getEmail(), dummyEmail);
    }

    @Test
    public void loginTest(){
        LoginDTO User = createUser(dummyEmail, dummyPassword);
        Response loginResponse = authServices.login(User);
        TokenDTO token = (TokenDTO) loginResponse.getEntity();
        assertEquals(Response.Status.OK.getStatusCode(), loginResponse.getStatus());
        assertEquals(Response.Status.OK.getStatusCode(), authServices.expirationCheck(token.getToken()).getStatus());
    }

    @Test
    public void loginTestInvalidEmail(){
        LoginDTO User = createUser(dummyEmail, dummyPassword);
        User.setEmail("nada@test.com");
        Response loginResponse = authServices.login(User);
        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), loginResponse.getStatus());
    }

    @Test
    public void loginTestInvalidPassword(){
        LoginDTO User = createUser(dummyEmail, dummyPassword);
        User.setPassword("password456");
        Response loginResponse = authServices.login(User);
        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), loginResponse.getStatus());
    }

    @Test
    public void currentUserTest(){
        LoginDTO User = createUser(dummyEmail, dummyPassword);
        Response loginResponse = authServices.login(User);
        TokenDTO token = (TokenDTO) loginResponse.getEntity();
        Response currentResponse = authServices.getCurrentUser(token.getToken());
        assertEquals(Response.Status.OK.getStatusCode(), currentResponse.getStatus());
        LoginDTO user = (LoginDTO) currentResponse.getEntity();
        assertEquals(user.getEmail(), User.getEmail());
    }

    public LoginDTO createUser(String email, String password){
        LoginDTO userToRegister = new LoginDTO();
        userToRegister.setEmail(email);
        userToRegister.setPassword(password);
        assertEquals(Response.Status.OK.getStatusCode(), authServices.register(userToRegister).getStatus());
        return new LoginDTO(email, password);
    }

}
