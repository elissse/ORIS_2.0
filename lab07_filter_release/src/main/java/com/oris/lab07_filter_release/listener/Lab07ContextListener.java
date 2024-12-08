package com.oris.lab07_filter_release.listener;

import com.oris.lab07_filter_release.db.DbWork;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@WebListener
public class Lab07ContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {
        DbWork.getInstance();
        Map<UUID, Long> userSessions = new HashMap<>();
        event.getServletContext().setAttribute("USER_SESSIONS", userSessions);
    }

    public void contextDestroyed(ServletContextEvent event) {
        DbWork.getInstance().destroy();
        Map userSessions = (Map) event.getServletContext().getAttribute("USER_SESSIONS");
        userSessions.clear();
    }
}
