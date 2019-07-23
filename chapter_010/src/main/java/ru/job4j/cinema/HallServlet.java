package ru.job4j.cinema;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class HallServlet extends HttpServlet {

    private List<Boolean> hall = new CopyOnWriteArrayList<>();
    private final Validate validate = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        hall = this.validate.getSeats();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(writer, hall);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer phone = Integer.parseInt(req.getParameter("phone"));
        String username = req.getParameter("username");
        Integer seat = Integer.parseInt(req.getParameter("place"));
        this.validate.add(new Account(username, phone), seat);
        resp.sendRedirect(String.format("%s/cinema", req.getContextPath()));
    }
}
