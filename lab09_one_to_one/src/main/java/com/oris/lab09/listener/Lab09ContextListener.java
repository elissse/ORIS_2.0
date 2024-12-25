package com.oris.lab09.listener;

import com.oris.lab09.repository.DbWork;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;


@WebListener
public class Lab09ContextListener implements ServletContextListener {
    private static final String URL = "jdbc:postgresql://localhost:5432/lab09_db";
    private static final String USER = "elise";
    private static final String PASSWORD = "superuser";

    final static Logger logger = LogManager.getLogger(Lab09ContextListener.class);

    public void contextInitialized(ServletContextEvent sce) {

        logger.info("start migration config");
        Flyway flyway = Flyway.configure().baselineOnMigrate(true).dataSource(URL, USER, PASSWORD).load();
        logger.info("start migration");
        flyway.migrate();
        logger.info("migration done");
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }
}
