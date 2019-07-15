package ru.job4j.crud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class UserController extends HttpServlet {

    private final Validate validate = ValidateService.getInstance();
    private final Map<String, Function<User, String>> actions = new HashMap<>();


    public UserController() {
        actions.put("add", this::add);
        actions.put("update", this::update);
        actions.put("delete", this::delete);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setAttribute("users", validate.findAll());
        req.getRequestDispatcher("/WEB-INF/views/UsersView.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html");
        String key = req.getParameter("action");
        int id = -1;
        if (req.getParameter("id") != null) {
            id = Integer.parseInt(req.getParameter("id"));
        }
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        String country = req.getParameter("country");
        String city = req.getParameter("city");
        HttpSession session = req.getSession();
        if (session.getAttribute("role").equals("administrator")) {
            this.actions.get(key).apply(new User(id, name, password, role, country, city));
            resp.sendRedirect(String.format("%s/users", req.getContextPath()));
        } else {
            req.setAttribute("error", "Access denied. Not enough access rights.");
            doGet(req, resp);
        }
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
