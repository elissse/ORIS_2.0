package org.oris.servlets_15_11_24;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.Writer;

// шаблонизатор - отдельный сервлет, занимающийся отрисовкой шаблонов

@WebServlet("/simple")
public class SimpleServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("call init servlet");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("call service");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Writer writer = servletResponse.getWriter();
        writer.write("hello!");
        writer.write(request.getMethod());
        writer.write(request.getRemoteAddr());
    }

    @Override
    public String getServletInfo() {
        return "";
    }

    @Override
    public void destroy() {
        System.out.println("call destroy servlet");
    }
}
