package com.todo.services;

import org.example.api.services.AuthServices;
import org.example.configs.MorphiaConfig;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.rules.ExternalResource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mongodb.morphia.Datastore;

public class TodoMainTest {
    @Mock
    private static Datastore mockDatastore;

    @InjectMocks
    public AuthServices authServices;

    @ClassRule
    public static ExternalResource externalResource = new ExternalResource() {
        @Override
        protected void after() { mockDatastore.getDB().dropDatabase(); }
    };

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockDatastore = new MorphiaConfig("MockDataBase").getDatastore();
        authServices.setDatastore(mockDatastore);
    }
}
