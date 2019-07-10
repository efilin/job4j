package ru.job4j.crud;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CitiesController extends HttpServlet {

    private final Validate validate = ValidateService.getInstance();
    private final Map<String, Country> countryMap = new ConcurrentHashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(writer, countryMap);
        writer.flush();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = req.getReader()) {
            if (reader != null) {
                sb.append(reader.readLine());
            }
        }
        String countryName = sb.toString().substring(1, sb.toString().length() - 1);
        List<String> cities = this.validate.getCities(countryName);
        Country country = new Country();
        country.setName(countryName);
        country.setCities(cities);
        if (!countryMap.containsKey(countryName)) {
            countryMap.put(countryName, country);
        }
    }
}
