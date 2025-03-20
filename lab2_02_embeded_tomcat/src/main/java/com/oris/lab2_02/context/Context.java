package com.oris.lab2_02.context;

import com.oris.lab2_02.annotations.*;
import com.oris.lab2_02.service.PathScan;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Context {

    // Конфигурирование и запуск tomcat
    // Поиск компонентов
    // Поиск членов с аннотацией @Inject
    // Внедрение зависимостей

    // Поиск контроллеров - класс, содержащий методы, которые обрабатывают разные http запрос формирование структуры

    private Map<String, Object> components;
    private Map<String, Object> controllers;
    private Map<String, MVSStructure> methods;

    private static Context instance;

    public static synchronized Context getInstance() {
        if (instance == null) {
            instance = new Context();
        }
        return instance;
    }

    public Map<String, MVSStructure> getMethods() {
        return methods;
    }

    private Context() {
        components = new HashMap<>();
        controllers = new HashMap<>();
        methods = new HashMap<>();
        scanComponent("com.oris.lab2_02");
        scanController("com.oris.lab2_02");
    }

    public class MVSStructure {
        public String path;
        public String httpMethod;
        public Object controller;
        public Method method;
    }

    private void scanComponent(String path) {
        List<Class<?>> classes = PathScan.find(path);
        for (Class c : classes) {
            if (c.getAnnotation(Component.class) != null) {
                try {
                    components.put(c.getName(), c.newInstance());
                } catch (InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        for (Class c : classes) {
            for (Field field : c.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.getAnnotation(Inject.class) != null) {
                    try {
                        if (components.get(c.getName()) != null)
                            field.set(components.get(c.getName()), components.get(field.getType().getName()));
                        else
                            field.set(controllers.get(c.getName()), controllers.get(field.getType().getName()));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        }
    }

    private void scanController(String pathh) {
        List<Class<?>> classes = PathScan.find(pathh);
        for (Class c : classes) {
            if (c.getAnnotation(Controller.class) != null) {
                try {
                    controllers.put(c.getName(), c.newInstance());
                } catch (InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                for (Method method : c.getDeclaredMethods()) {
                    method.setAccessible(true);
                    if (method.getAnnotation(PostRequest.class) != null) {
                        String path = method.getAnnotation(PostRequest.class).value();
                        MVSStructure route = new MVSStructure();
                        route.path = path;
                        route.httpMethod = "POST";
                        route.controller = controllers.get(c.getName());
                        route.method = method;
                        methods.put("POST:" + path, route);
                        System.out.println("POST:" + path);
                    } else if (method.getAnnotation(GetRequest.class) != null) {
                        String path = method.getAnnotation(GetRequest.class).value();
                        MVSStructure route = new MVSStructure();
                        route.path = path;
                        route.httpMethod = "GET";
                        route.controller = controllers.get(c.getName());
                        route.method = method;
                        methods.put("GET:" + path, route);
                        System.out.println("GET:" + path);
                    }
                }
            }
        }
    }
}
