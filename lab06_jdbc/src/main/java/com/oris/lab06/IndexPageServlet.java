package com.oris.lab06;

import com.oris.lab06.model.User;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@WebServlet("")
public class IndexPageServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        try {
            response.setContentType("text/html;charset=UTF-8");
            Writer writer = response.getWriter();

//            Connection connection =
//                    DriverManager.getConnection(
//                            // адрес БД , имя пользователя, пароль
//                            "jdbc:postgresql://localhost:5432/agona_db", "elise", "superuser");

            System.out.println("all good");
            ServletContext ctx = getServletContext();
            List<User> users = (List<User>) ctx.getAttribute("users");
            request.setAttribute("users", users);
            users.forEach(System.out::println);
            request.setAttribute("title_page", "database userssssssssss");
            request.getRequestDispatcher("/templates/index.ftl").forward(request, response);


        }  catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }


    }

}