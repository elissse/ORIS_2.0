package com.oris.lab2_04.helper;

import java.util.*;

public class GraphSorter {
    public static List<Class<?>> topologicalSort(List<Class<?>> entityClasses) {
        Map<Class<?>, List<Class<?>>> graph = new HashMap<>();
        Map<Class<?>, Integer> inDegree = new HashMap<>();
        for (Class<?> clazz : entityClasses) {
            graph.putIfAbsent(clazz, new ArrayList<>());
            inDegree.putIfAbsent(clazz, 0);
        }
        for (Class<?> clazz : entityClasses) {
            List<Class<?>> dependencies = DependencyResolver.getDependencies(clazz);
            for (Class<?> dep : dependencies) {
                graph.get(dep).add(clazz); // Зависимость: dep -> clazz
                inDegree.put(clazz, inDegree.getOrDefault(clazz, 0) + 1);
            }
        }
        Queue<Class<?>> queue = new LinkedList<>();
        for (Class<?> clazz : inDegree.keySet()) {
            if (inDegree.get(clazz) == 0) {
                queue.add(clazz);
            }
        }
        List<Class<?>> sorted = new ArrayList<>();
        while (!queue.isEmpty()) {
            Class<?> current = queue.poll();
            sorted.add(current);
            for (Class<?> neighbor : graph.get(current)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
        }
        return sorted;
    }
}
