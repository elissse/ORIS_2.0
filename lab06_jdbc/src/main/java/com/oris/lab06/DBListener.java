package com.oris.lab06;

import com.oris.lab06.model.User;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebListener

public class DBListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        // create connection
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(new User(resultSet.getLong("id"), resultSet.getString("name")));
            }
            resultSet.close();
            preparedStatement.close();
            ServletContext ctx = sce.getServletContext();
            ctx.setAttribute("users", users);
            ctx.getAttributeNames().asIterator().forEachRemaining(System.out::println);
            users.forEach(System.out::println);

            System.out.println("HIIHUFhiufhf");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
        Connection connection = DBConnection.getConnection();
        DBConnection.releaseConnection(connection);
    }
}
