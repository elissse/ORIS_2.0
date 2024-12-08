package com.oris.lab07_auth;

import com.oris.lab07_auth.model.User;
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
public class Lab07ContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Connection connection = DbWork.getInstance().getConnection();
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
        Map<UUID, Long> userSessions = new HashMap<>();
        sce.getServletContext().setAttribute("USER_SESSIONS", userSessions);
    }

    public void contextDestroyed(ServletContextEvent sce) {
        DbWork.getInstance().destroy();
        Map userSessions = (Map) sce.getServletContext().getAttribute("USER_SESSIONS");
        userSessions.clear();
    }
}
