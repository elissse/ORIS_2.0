package com.oris.lab09.controller;

import com.oris.lab09.service.ClientService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet("/index")
public class IndexPageServlet extends HttpServlet {
    final static Logger logger = LogManager.getLogger(IndexPageServlet.class);
    private ClientService service = new ClientService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            System.out.println("hi bitch");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
