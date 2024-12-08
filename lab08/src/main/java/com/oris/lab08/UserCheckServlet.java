package com.oris.lab08;

import com.oris.lab08.listener.Lab08ContextListener;
import com.oris.lab08.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebServlet("/usercheck")
public class UserCheckServlet extends HttpServlet {
    final static Logger logger = LogManager.getLogger(UserCheckServlet.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username+ ' '+password);
        if (validate(username, password)) {
            User user = getUser(username, password);
            HttpSession session = request.getSession();
            if (session.getAttribute("uuid") == null  || !checkUUID((String) session.getAttribute("uuid"))) {
                UUID uuid = UUID.randomUUID();
                session.setAttribute("uuid", uuid.toString());
                session.setAttribute("username", username);
                session.setMaxInactiveInterval(3 * 60);
                writeToSession(session, user);
            }
            response.sendRedirect("index.ftl");
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.ftl");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Either user name or password is wrong.</font>");
            rd.include(request, response);
        }

    }

    private User getUser(String username, String password) {
        ServletContext ctx = getServletContext();
        List<User> users = (List<User>) ctx.getAttribute("users");
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        for (User user : users) {
            System.out.println(user.getUsername());
            System.out.println(user.getPassword());
            if (user.getUsername().equals(username) && bCrypt.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    private boolean validate(String username, String password) {
        System.out.println("hiiii");
        return getUser(username, password) != null;

    }

    private boolean checkUUID(String uuid) {
        ServletContext ctx = getServletContext();
        Map<UUID, Long> userSessions = (Map<UUID, Long>) ctx.getAttribute("USER_SESSIONS");
        return userSessions.containsKey(UUID.fromString(uuid));
    }

    private void writeToSession(HttpSession session, User user) {
        ServletContext ctx = getServletContext();
        Map<UUID, Long> userSessions = (Map<UUID, Long>) ctx.getAttribute("USER_SESSIONS");
        userSessions.put(UUID.fromString((String) session.getAttribute("uuid")), user.getId());
        ctx.setAttribute("USER_SESSIONS", userSessions);
    }
}