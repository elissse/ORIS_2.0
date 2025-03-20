package com.oris.lab2_02.servlets;

import com.oris.lab2_02.context.Context;
import com.oris.lab2_02.context.Context.MVSStructure;
import com.oris.lab2_02.controllers.TestController;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet("/*")
public class DispatcherServlet extends HttpServlet {

    private Context context;

    @Override
    public void init() throws ServletException {
        super.init();
        context = Context.getInstance();
    }

    public void service(HttpServletRequest request, HttpServletResponse response) {

        String httpMethod = request.getMethod();
        String path = request.getPathInfo();
        if ("/favicon.ico".equals(path)) {
            return;
        }
        MVSStructure route = null;
        System.out.println("%s:%s".formatted(httpMethod, path));

        if (context.getMethods().containsKey("%s:%s".formatted(httpMethod, path))) {
            route = (MVSStructure) context.getMethods().get("%s:%s".formatted(httpMethod, path));
        }
        if (route != null) {
            try {
                route.method.invoke(route.controller, request, response);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("404 Not Found - No mapping for " + httpMethod + ":" + path);
        }


    }


}