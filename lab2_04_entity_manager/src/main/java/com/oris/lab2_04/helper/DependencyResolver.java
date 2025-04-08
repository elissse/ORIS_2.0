package com.oris.lab2_04.helper;

import com.oris.lab2_04.annotation.ManyToOne;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class DependencyResolver {
    public static List<Class<?>> getDependencies(Class<?> entityClass) {
        List<Class<?>> dependencies = new ArrayList<>();
        for (Field field : entityClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(ManyToOne.class)) {
                dependencies.add(field.getType());
            }
        }
        return dependencies;
    }
}