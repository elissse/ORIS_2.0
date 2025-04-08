package com.oris.lab2_04.orm;


import com.oris.lab2_04.annotation.Entity;
import com.oris.lab2_04.service.PathScan;

import java.util.ArrayList;
import java.util.List;

public class Context {
    private List<Class<?>> entities;

    private static Context instance;

    public static synchronized Context getInstance() {
        if (instance == null) {
            instance = new Context();
        }
        return instance;
    }

    private Context() {
        entities = new ArrayList<>();
        scanEntities("com.oris.lab2_04.model");
    }

    public List<Class<?>> getEntities() {
        return entities;
    }

    private void scanEntities(String path) {
        List<Class<?>> classes = PathScan.find(path);
        for (Class c : classes) {
            if (c.getAnnotation(Entity.class) != null) {
                entities.add(c);
            }
        }
    }
}
