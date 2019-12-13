package ru.job4j.storage;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.job4j.beanexamples.User;

import static org.junit.Assert.*;

public class ImportUserTest {

    @Test
    public void whenAddUserToStorage() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Storage memoryStorage = context.getBean(MemoryStorage.class);
        memoryStorage.add(new User("Mike"));
        assertNotNull(memoryStorage);
    }
}