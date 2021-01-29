package ru.job4j.beanexamples;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void whenCreateBeanWithXmlConfigurationWithConstructorInjection() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        User userOne = context.getBean("userOne", User.class);
        assertEquals(userOne.getName(), "Sasha");
    }

    @Test
    public void whenCreateBeanWithXmlConfigurationWithSetterInjection() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        User userOne = context.getBean("userTwo", User.class);
        assertEquals(userOne.getName(), "Kolya");
    }

    @Test
    public void whenCreateBeanWithAnnotationConfigurationWithConstructorInjection() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        User userThree = context.getBean(User.class);
        assertEquals(userThree.getName(), "Masha");
    }

}