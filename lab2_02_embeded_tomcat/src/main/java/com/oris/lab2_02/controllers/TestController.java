package com.oris.lab2_02.controllers;

import com.oris.lab2_02.annotations.Controller;
import com.oris.lab2_02.annotations.GetRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class TestController {

    @GetRequest("/test")
    public void getTestPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.println("<html><head><meta charset='utf-8'/><title>Embeded Tomcat</title></head><body>");
        writer.println("<h1>Тестовая страница!</h1>");

        writer.println("<div>Метод: " + request.getMethod() + "</div>");
        writer.println("<div>Ресурс: " + request.getPathInfo() + "</div>");
        writer.println("</body></html>");

    }

    @GetRequest("/home")
    public void getHomePage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.println("<html><head><meta charset='utf-8'/><title>Embeded Tomcat</title></head><body>");
        writer.println("<h1>Домашняя страница!</h1>");

        writer.println("<div>Метод: " + request.getMethod() + "</div>");
        writer.println("<div>Ресурс: " + request.getPathInfo() + "</div>");
        writer.println("</body></html>");
    }

}
