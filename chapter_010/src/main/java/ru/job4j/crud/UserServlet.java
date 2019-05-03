package ru.job4j.crud;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        String buttonCreate = "<form action='" + req.getContextPath() + "/list' method= 'post'>"
                + "<input type='hidden' name='action' value='add'/>"
                + "Name : <input type='text' name='name'/>"
                + "<input type='submit' value='Create'>"
                + "</form>";

        StringBuilder sb = new StringBuilder("<table>");
        for (User name : validate.findAll()) {
            String buttonUpdate = "<form action='/update' method= 'get'>"
                    + "<input type='hidden' name='action' value='update'/>"
                    + "<input type='hidden' name='id' value=" + name.getId() + ">"
                    + "<input type='submit' value='update'>"
                    + "</form>";
            String buttonDelete = "<form action= '/list' method='post'>"
                    + "<input type='hidden' name='action' value='delete'/>"
                    + "<input type='hidden' name='id' value=" + name.getId() + ">"
                    + "<input type='submit' value='delete'>"
                    + "</form>";
            sb.append("<tr><td>" + name.getName() + "</td><td>" + buttonUpdate + "</td><td>" + buttonDelete + "</td></tr>");
        }
        sb.append("</table>");

        writer.append("<!DOCTYPE html>"
                + "<html lang='en'>"
                + "<head>"
                + "<h3> List of all users </h3>"
                + "<meta charset='UTF-8'>"
                + "<title>All Users</title>"
                + "</head>"
                + "<body>"
                + buttonCreate
                + sb.toString()
                + "</body>"
                + "</html>");

        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        String key = req.getParameter("action");
        int id = -1;
        if (req.getParameter("id") != null) {
            id = Integer.parseInt(req.getParameter("id"));
        }
        String name = req.getParameter("name");
        writer.append(this.actions.get(key).apply(new User(id, name)));
        writer.flush();
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
