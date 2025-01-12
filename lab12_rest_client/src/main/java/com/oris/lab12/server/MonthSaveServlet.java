package com.oris.lab12.server;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oris.lab12.mapper.MonthMapper;
import com.oris.lab12.model.Month;
import com.oris.lab12.repository.MonthRepository;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/month/save")
public class MonthSaveServlet extends HttpServlet {
    private final MonthRepository repository = new MonthRepository(new MonthMapper());

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        Month month = mapper.readValue(request.getReader(), new TypeReference<Month>() {
        });
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        System.out.println(month.getName() + month.getSeason());
        mapper.writeValue(response.getWriter(), repository.save(month));

    }
}
