package ru.job4j.crud;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class UserControllerTest {
    private Validate validate = new ValidateStub();


    @Test
    public void whenAddUserThenStoreIt() throws ServletException, IOException {
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("name")).thenReturn("Ivan");
        when(req.getParameter("action")).thenReturn("add");
        HttpSession session = mock(HttpSession.class);
        when(req.getSession()).thenReturn(session);
        when(req.getSession().getAttribute("role")).thenReturn("administrator");
        new UserController().doPost(req, resp);
        assertThat(validate.findAll().iterator().next().getName(), is("Ivan"));
    }

    @Test
    public void whenAddUserThenUpdateIt() throws IOException, ServletException {
        validate.add(new User("Bill", "root", "administrator", "Russia", "Moscow"));
        int id = validate.findAll().iterator().next().getId();
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn(Integer.toString(id));
        when(req.getParameter("name")).thenReturn("Boris");
        when(req.getParameter("password")).thenReturn("root");
        when(req.getParameter("role")).thenReturn("administrator");
        when(req.getParameter("action")).thenReturn("update");
        HttpSession session = mock(HttpSession.class);
        when(req.getSession()).thenReturn(session);
        when(req.getSession().getAttribute("role")).thenReturn("administrator");
        new UserController().doPost(req, resp);
        assertThat(validate.findAll().iterator().next().getName(), is("Boris"));
    }

    @Test
    public void whenAddUserThenDeleteIt() throws IOException, ServletException {
        validate.add(new User("Bill", "root", "administrator", "Russia", "Moscow"));
        validate.add(new User("Alex", "root", "administrator", "Russia", "Moscow"));
        int id = validate.findAll().iterator().next().getId();
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn(Integer.toString(id));
        when(req.getParameter("action")).thenReturn("delete");
        HttpSession session = mock(HttpSession.class);
        when(req.getSession()).thenReturn(session);
        when(req.getSession().getAttribute("role")).thenReturn("administrator");
        new UserController().doPost(req, resp);
        assertThat(validate.findAll().iterator().next().getName(), is("Alex"));
    }

    @After
    public void tearDown() {
        this.validate.findAll()
                .forEach(u -> this.validate.delete(u.getId()));
    }
}