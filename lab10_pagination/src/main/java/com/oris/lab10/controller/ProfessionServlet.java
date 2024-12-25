package com.oris.lab10.controller;

import com.oris.lab10.model.Profession;
import com.oris.lab10.service.ProfessionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/professions")
public class ProfessionServlet extends HttpServlet {
    private final ProfessionService service = new ProfessionService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(false);
            response.setContentType("text/html;charset=UTF-8");
            int rows = service.countRows();
            int currentPage;
            if (request.getParameter("page") != null) {
                System.out.println(request.getParameter("page"));
                currentPage = Integer.parseInt(request.getParameter("page"));
            } else {
                currentPage = 1;
            }
            String name;
            if (request.getParameter("profession") != null) {
                name = request.getParameter("profession").toUpperCase();
                session.setAttribute("name", name);
            } else
                name = (String) session.getAttribute("name");
            List<Profession> professions;
            if (currentPage == 0) {
                currentPage++;
                professions = getProfessionsOnPage(name, 0);
            } else if (rows + 1 == currentPage) {
                currentPage--;
                professions = getProfessionsOnPage(name, currentPage - 1);
            } else {
                professions = getProfessionsOnPage(name, currentPage - 1);
            }
            System.out.println(name);
            request.setAttribute("name", name);
            System.out.println();
            request.setAttribute("page", currentPage);
            System.out.println(currentPage);
            request.setAttribute("profession_list", professions);
            request.setAttribute("template", "p.ftl");
            request.getRequestDispatcher("/templates/p.ftl").forward(request, response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Profession> getProfessionsOnPage(String name, int page) {
        return service.getAllByName(name, page);
    }

}