package com.oris.lab_07_filter;

import com.oris.lab_07_filter.model.User;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@WebListener
public class FilterContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        Map<UUID, Long> authenticationData = new HashMap<>();
        sce.getServletContext().setAttribute("AUTH_DATA", authenticationData);
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(new User(resultSet.getLong("id"), resultSet.getString("username"), resultSet.getString("password")));
            }
            resultSet.close();
            preparedStatement.close();
            ServletContext ctx = sce.getServletContext();
            ctx.setAttribute("users", users);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
        Map userSessions = (Map) sce.getServletContext().getAttribute("AUTH_DATA");
        Connection connection = DBConnection.getConnection();
        DBConnection.releaseConnection(connection);
    }
}
