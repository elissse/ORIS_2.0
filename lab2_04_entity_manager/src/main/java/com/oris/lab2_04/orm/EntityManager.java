package com.oris.lab2_04.orm;

import java.util.List;

public interface EntityManager {

    <T> T save(T entity);
    void remove(Object entity);
    <T> T find(Class<T> entityType, Object key);
    <T> List<T> findAll(Class<T> entityType);
}