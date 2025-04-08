package com.oris.lab2_04.servlets;

import com.oris.lab2_04.config.Config;
import com.oris.lab2_04.model.Model;
import com.oris.lab2_04.view.View;
import com.oris.lab2_04.view.ViewResolver;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/*")
public class DispatcherServlet extends HttpServlet {

    private Map<String, MVSStructure> routes = new HashMap<>();
    private ApplicationContext context;

    @Override
    public void init() throws ServletException {
        super.init();
        context = new AnnotationConfigApplicationContext(Config.class);
        System.out.println("hi");
        scan();
    }

    public void scan() {
        Map<String, Object> controllers = context.getBeansWithAnnotation(Controller.class);
        System.out.println(controllers.size());
        for (Object controller : controllers.values()) {
            for (Method method: controller.getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(GetMapping.class)) {
                    String[] paths = method.getAnnotation(GetMapping.class).value();
                    for (String path: paths) {
                        MVSStructure route = new MVSStructure();
                        route.path = path;
                        route.httpMethod = "GET";
                        route.controller = controller;
                        route.method = method;
                        routes.put("GET:" + path, route);
                    }
                } else if (method.isAnnotationPresent(PostMapping.class)) {
                    String[] paths = method.getAnnotation(PostMapping.class).value();
                    for (String path: paths) {
                        MVSStructure route = new MVSStructure();
                        route.path = path;
                        route.httpMethod = "POST";
                        route.controller = controller;
                        route.method = method;
                        routes.put("POST:" + path, route);
                    }
                }
            }
        }
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getPathInfo();
        String httpMethod = request.getMethod().toUpperCase();
        if ("/favicon.ico".equals(path)) return;
        MVSStructure route = routes.get("%s:%s".formatted(httpMethod, path));
        if (route != null && routes.containsKey("%s:%s".formatted(httpMethod, path))) {
            try {
                Model model = (Model) route.method.invoke(route.controller, request, response);
                View view = new ViewResolver(model).resolve();
                view.render(response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "404 - No mapping for " + httpMethod + ":" + path);
        }
    }

    public class MVSStructure {
        public String path;
        public String httpMethod;
        public Object controller;
        public Method method;
    }

}