package ru.job4j.crud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        HttpSession session = req.getSession();
        if (session.getAttribute("role").equals("user") && req.getParameter("role").equals("administrator")) {
            req.setAttribute("error", "Access denied. Not enough access rights.");
            doGet(req, resp);
        } else {
            int id = this.validate.findById(Integer.parseInt(req.getParameter("id"))).getId();
            this.validate.update(id, new User(
                    req.getParameter("name"),
                    req.getParameter("password"),
                    req.getParameter("role"),
                    req.getParameter("country"),
                    req.getParameter("city")));
            resp.sendRedirect(String.format("%s/users", req.getContextPath()));
        }
    }
}
