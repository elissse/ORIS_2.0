package com.oris.lab09.controller;

import com.oris.lab09.model.Client;
import com.oris.lab09.model.ClientInfo;
import com.oris.lab09.service.ClientService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebServlet("/client")
public class ClientPageServlet extends HttpServlet {
    private final ClientService service = new ClientService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClientInfo clientInfo = new ClientInfo(request.getParameter("phone"),request.getParameter("address"),request.getParameter("passport"));
        Client client = new Client(0L, request.getParameter("name"), request.getParameter("email"), clientInfo);
       // service.save(client);
        Client gotClient = service.findById(1L);
        System.out.println(gotClient);
        service.getAll().forEach(System.out::println);
    }
}
