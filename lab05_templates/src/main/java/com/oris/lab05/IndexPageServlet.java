package com.oris.lab05;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("")
public class IndexPageServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("title", "Hello from Jakarta Servlet!");
        request.setAttribute("mess", "hello me");


        // Формирование параметров для шаблона
        // request.setAttribute("param1", "param1_value");
        //request.setAttribute("param2", "param2_value");

        try {
            // Перенаправляем запрос к сервлету-шаблонизатору
            // Указываем ресурс для обработки
            request.getRequestDispatcher("/template/index.thtml").forward(request, response);

        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
