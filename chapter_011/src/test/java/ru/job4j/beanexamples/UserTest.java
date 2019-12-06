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
        assertThat(userOne.getName(), is("Sasha"));
    }

    @Test
    public void whenCreateBeanWithXmlConfigurationWithSetterInjection() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        User userOne = context.getBean("userTwo", User.class);
        assertThat(userOne.getName(), is("Kolya"));
    }

    @Test
    public void whenCreateBeanWithAnnotationConfigurationWithConstructorInjection() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        User userThree = context.getBean(User.class);
        assertThat(userThree.getName(), is("Masha"));
    }

}