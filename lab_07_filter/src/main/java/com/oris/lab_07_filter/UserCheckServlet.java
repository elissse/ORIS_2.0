package com.oris.lab_07_filter;

import com.oris.lab_07_filter.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebServlet("/usercheck")
public class UserCheckServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (validate(username, password)) {
            User user = getUser(username, password);
            HttpSession session = request.getSession();
            if (session.getAttribute("uuid") == null || !checkUUID((String) session.getAttribute("uuid"))) {
                UUID uuid = UUID.randomUUID();
                session.setAttribute("uuid", uuid.toString());
                session.setMaxInactiveInterval(3 * 60);

                Cookie userCookie = new Cookie("uuid", uuid.toString());
                userCookie.setMaxAge(3 * 60);
                response.addCookie(userCookie);
                writeToCookies(userCookie, user);
            }
            response.sendRedirect("login_success.jsp");
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
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    private boolean validate(String username, String password) {
        return getUser(username, password) != null;
    }

    private boolean checkUUID(String uuid) {
        ServletContext ctx = getServletContext();
        Map<UUID, Long> authenticationData = (Map<UUID, Long>) ctx.getAttribute("AUTH_DATA");
        return authenticationData.containsKey(UUID.fromString(uuid));
    }

    private void writeToCookies(Cookie cookie, User user) {
        ServletContext ctx = getServletContext();
        Map<UUID, Long> authenticationData = (Map<UUID, Long>) ctx.getAttribute("AUTH_DATA");
        authenticationData.put(UUID.fromString(cookie.getValue()), user.getId());
        ctx.setAttribute("AUTH_DATA", authenticationData);
    }

}
