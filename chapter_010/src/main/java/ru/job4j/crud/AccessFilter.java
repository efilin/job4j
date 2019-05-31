package ru.job4j.crud;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AccessFilter implements Filter {

    private final Validate validate = ValidateService.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        User user = ValidateService.getInstance().findById(Integer.parseInt(req.getParameter("id")));
        if (session.getAttribute("role").equals("administrator")
                || session.getAttribute("name").equals(user.getName())) {
            chain.doFilter(request, response);
        } else {
            req.setAttribute("error", "Access denied. Not enough access rights.");
            req.setAttribute("users", validate.findAll());
            HttpServletResponse resp = (HttpServletResponse) response;
            req.getRequestDispatcher("/WEB-INF/views/UsersView.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
