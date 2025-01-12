package com.oris.lab12.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oris.lab12.mapper.MonthMapper;
import com.oris.lab12.repository.MonthRepository;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/month/delete/*")
public class MonthDeleteServlet extends HttpServlet {
    private final MonthRepository repository = new MonthRepository(new MonthMapper());

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        String idString = request.getPathInfo().substring(1);
        int id = 0;
        if (isInteger(idString)) {
            id = Integer.parseInt(idString) ;
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        mapper.writeValue(response.getWriter(), repository.deleteById(Long.valueOf(id)));

    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }
}
