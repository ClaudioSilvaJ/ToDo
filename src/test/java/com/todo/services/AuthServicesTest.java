package com.todo.services;

import static org.junit.Assert.assertEquals;
import javax.ws.rs.core.Response;
import org.example.api.services.AuthServices;
import org.example.configs.MorphiaConfig;
import org.example.dtos.LoginDTO;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mongodb.morphia.Datastore;

@FixMethodOrder(MethodSorters.JVM)
public class AuthServicesTest {

    public String dummyEmail = "test@example.com";
    public String dummyPassword = "password123";

    @Mock
    private static Datastore mockDatastore = new MorphiaConfig("test").getDatastore();

    @InjectMocks
    private AuthServices authServices;

    @ClassRule
    public static ExternalResource externalResource = new ExternalResource() {
        @Override
        protected void after() {
            mockDatastore.getDB().dropDatabase();
        }
    };

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockDatastore = new MorphiaConfig("MockDataBase").getDatastore();
    }

    @Test
    public void AuthTest() {
        authServices.setDatastore(mockDatastore);
        LoginDTO userToRegister = new LoginDTO();
        userToRegister.setEmail(dummyEmail);
        userToRegister.setPassword(dummyPassword);
        assertEquals(Response.Status.OK.getStatusCode(), authServices.register(userToRegister).getStatus());

        LoginDTO userToLogin = new LoginDTO();
        userToLogin.setEmail("test@example.com");
        userToLogin.setPassword("password123");
        assertEquals(Response.Status.OK.getStatusCode(), authServices.login(userToLogin).getStatus());
    }
}
