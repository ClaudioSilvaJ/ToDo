package com.todo.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.ws.rs.core.Response;

import org.example.api.services.AuthServices;
import org.example.configs.MorphiaConfig;
import org.example.dtos.LoginDTO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mongodb.morphia.Datastore;

public class AuthServicesTest {

    @Mock
    private Datastore mockDatastore;

    @InjectMocks
    private AuthServices authServices;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser() {
        LoginDTO userToRegister = new LoginDTO();
        userToRegister.setEmail("test@example.com");
        userToRegister.setPassword("password123");
        when(mockDatastore.save(userToRegister)).thenReturn(null);
        Response response = authServices.register(userToRegister);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        Response response2 = authServices.login(userToRegister);
        assertEquals(Response.Status.OK.getStatusCode(), response2.getStatus());

    }

    @Test
    public void testLoginUser(){
        LoginDTO userToLogin = new LoginDTO();
        userToLogin.setEmail("test@example.com");
        userToLogin.setPassword("password123");
        Response response = authServices.login(userToLogin);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

}
