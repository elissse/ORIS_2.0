package com.oris.lab08.listener;

import com.oris.lab08.model.User;
import com.oris.lab08.repository.DbWork;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@WebListener
public class Lab08ContextListener implements ServletContextListener {
    private static final String URL = "jdbc:postgresql://localhost:5432/oris_db";
    private static final String USER = "elise";
    private static final String PASSWORD = "superuser";

    final static Logger logger = LogManager.getLogger(Lab08ContextListener.class);

    public void contextInitialized(ServletContextEvent sce) {
        // Create the Flyway instance and point it to the database

        logger.info("start migration config");

        Flyway flyway = Flyway.configure().baselineOnMigrate(true).dataSource(URL, USER, PASSWORD).load();
        System.out.println("hiiiiiiiiiiiiii");
        logger.info("start migration");

        // Start the migration
        flyway.migrate();
        logger.info("migration done");
        try {
            Connection connection = DbWork.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM hash_users");
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
        // Закрываем подключение
        DbWork.getInstance().destroy();
        Map userSessions = (Map) sce.getServletContext().getAttribute("USER_SESSIONS");
        userSessions.clear();
    }
}
