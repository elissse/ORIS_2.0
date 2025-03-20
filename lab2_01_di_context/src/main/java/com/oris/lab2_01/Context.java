package com.oris.lab2_01;

import com.oris.lab2_01.annotation.Component;
import com.oris.lab2_01.annotation.Inject;
import com.oris.lab2_01.service.PathScan;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Context {

    private Map<String, Object> components;

    public Context() {
        components = new HashMap<>();
        scanComponent();
    }

    private void scanComponent() {
        List<Class<?>> classes = PathScan.find("com.oris.lab2_01");
        for (Class c : classes) {
            if (c.getAnnotation(Component.class) != null) {
                try {
                    components.put(c.getName(), c.newInstance());
                }  catch (InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        for (Class c: classes) {
            for (Field field : c.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.getAnnotation(Inject.class) != null) {
                    try {
                        field.set( components.get(c.getName()), components.get(field.getType().getName()));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public Object getObjectByName(String className) {
        return components.get(className);
    }

}