package ru.job4j.crud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserUpdateServlet extends HttpServlet {

    private final Validate validate = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        User user = this.validate.findById(Integer.parseInt(req.getParameter("id")));
        writer.append("<!DOCTYPE html>"
                + "<html lang='en'>"
                + "<head>"
                + "<h3> Update user </h3>"
                + "<meta charset='UTF-8'>"
                + "<title>Update user</title>"
                + "</head>"
                + "<body>"
                + "<form action=/update method='post'"
                + "Name:<br>"
                + "<input type='text' name='name' value='"
                + user.getName()
                + "'><br>"
                + "<input type='hidden' name='id' value="
                + Integer.toString(user.getId())
                + ">"
                + "  <input type='submit' value='Update'>"
                + " </form>"
                + "</body>"
                + "</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        int id = this.validate.findById(Integer.parseInt(req.getParameter("id"))).getId();
        writer.append(this.validate.update(id, new User(req.getParameter("name"))));
        writer.flush();
    }
}
