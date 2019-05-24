package ru.job4j.crud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserUpdateController extends HttpServlet {

    private final Validate validate = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = ValidateService.getInstance().findById(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("user", user);
        req.getRequestDispatcher("/WEB-INF/views/update/UpdateView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        int id = this.validate.findById(Integer.parseInt(req.getParameter("id"))).getId();
        this.validate.update(id, new User(req.getParameter("name")));
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
