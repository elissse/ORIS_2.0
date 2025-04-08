package com.oris.lab2_04.controller;

import com.oris.lab2_04.model.Model;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/test")
    public Model getTestPage(HttpServletRequest request, HttpServletResponse response) {

        Model model = new Model("test");
        model.add("param1", "value1");
        model.add("param2", "value2");
        return model;
    }

    @GetMapping("/home")
    public Model getHomePage(HttpServletRequest request, HttpServletResponse response) {
        return new Model("home");
    }

}