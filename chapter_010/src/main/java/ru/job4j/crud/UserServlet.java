package ru.job4j.crud;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class UserServlet extends HttpServlet {

    private final Validate validate = ValidateService.getInstance();
    private final Map<String, Function<User, String>> actions = new HashMap<>();


    public UserServlet() {
        actions.put("add", this::add);
        actions.put("update", this::update);
        actions.put("delete", this::delete);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(String.format("%s/index.jsp", req.getContextPath()));

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
       String key = req.getParameter("action");
        int id = -1;
        if (req.getParameter("id") != null) {
            id = Integer.parseInt(req.getParameter("id"));
        }
        String name = req.getParameter("name");
        this.actions.get(key).apply(new User(id, name));
        resp.sendRedirect(String.format("%s/index.jsp", req.getContextPath()));
    }


    public String add(User user) {
        return this.validate.add(user);
    }

    public String update(User user) {
        return this.validate.update(user.getId(), user);
    }

    public String delete(User user) {
        return this.validate.delete(user.getId());
    }

}
