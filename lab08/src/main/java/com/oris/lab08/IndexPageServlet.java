package com.oris.lab08;

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

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {

            String username = (String) request.getSession(false).getAttribute("username");

            logger.debug(username);
            logger.info(username + "-info");
            logger.error(username + "-error");

            request.getRequestDispatcher("index.ftl").forward(request, response);
            request.setAttribute("username", username);
            request.getRequestDispatcher("index.ftl").forward(request, response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }


    }

}