package com.todo;

import com.todo.services.AuthServicesTest;
import com.todo.services.TaskServicesTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AuthServicesTest.class,
        TaskServicesTest.class
})
public class TodoMainTests {
}
