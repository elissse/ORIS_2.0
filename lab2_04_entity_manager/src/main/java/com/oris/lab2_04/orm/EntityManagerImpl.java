package com.oris.lab2_04.orm;

import com.oris.lab2_04.annotation.Entity;
import com.oris.lab2_04.annotation.Id;
import com.oris.lab2_04.annotation.ManyToOne;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EntityManagerImpl implements EntityManager {
    private final Connection connection;

    public EntityManagerImpl(Connection connection) {
        this.connection = connection;
    }

    public void executeQuery(String query) {
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T save(T entity) {
        Class<?> entityClass = entity.getClass();
        try {
            Field idField = getIdField(entityClass);
            Object idValue = idField.get(entity);
            return idValue == null ? insert(entity, entityClass) : update(entity, entityClass);
        } catch (Exception e) {
            throw new RuntimeException("Save failed", e);
        }
    }

    @Override
    public void remove(Object entity) {
        Class<?> entityClass = entity.getClass();
        try {
            Field idField = getIdField(entityClass);
            String tableName = getTableName(entityClass);
            String sql = "DELETE FROM " + tableName + " WHERE " + idField.getName() + " = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setObject(1, idField.get(entity));
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            throw new RuntimeException("Remove failed", e);
        }
    }

    @Override
    public <T> T find(Class<T> entityType, Object key) {
        try {
            Field idField = getIdField(entityType);
            String tableName = getTableName(entityType);
            String sql = "SELECT * FROM " + tableName + " WHERE " + idField.getName() + " = ?";

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setObject(1, key);
                ResultSet rs = stmt.executeQuery();
                return rs.next() ? mapRow(rs, entityType) : null;
            }
        } catch (Exception e) {
            throw new RuntimeException("Find failed", e);
        }
    }

    @Override
    public <T> List<T> findAll(Class<T> entityType) {
        List<T> result = new ArrayList<>();
        try {
            String tableName = getTableName(entityType);
            String sql = "SELECT * FROM " + tableName;

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    result.add(mapRow(rs, entityType));
                }
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Find all failed", e);
        }
    }

    private <T> T insert(T entity, Class<?> entityClass) throws Exception {
        List<Field> fields = getNonIdFields(entityClass);
        String tableName = getTableName(entityClass);
        StringBuilder columns = new StringBuilder();
        StringBuilder placeholders = new StringBuilder();

        for (Field field : fields) {
            columns.append(getColumnName(field)).append(", ");
            placeholders.append("?, ");
        }

        String sql = String.format(
                "INSERT INTO %s (%s) VALUES (%s)",
                tableName,
                columns.substring(0, columns.length() - 2),
                placeholders.substring(0, placeholders.length() - 2)
        );

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            setParameters(stmt, entity, fields);
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                setGeneratedId(generatedKeys, entity, getIdField(entityClass));
            }
            return entity;
        }
    }

    private <T> T update(T entity, Class<?> entityClass) throws Exception {
        List<Field> fields = getNonIdFields(entityClass);
        String tableName = getTableName(entityClass);
        StringBuilder updates = new StringBuilder();
        Field idField = getIdField(entityClass);

        for (Field field : fields) {
            updates.append(getColumnName(field)).append(" = ?, ");
        }

        String sql = String.format("UPDATE %s SET %s WHERE %s = ?",
                tableName,
                updates.substring(0, updates.length() - 2),
                idField.getName());

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            setParameters(stmt, entity, fields);
            stmt.setObject(fields.size() + 1, idField.get(entity));
            stmt.executeUpdate();
            return entity;
        }
    }

    private <T> T mapRow(ResultSet rs, Class<T> entityType) throws Exception {
        T entity = entityType.getDeclaredConstructor().newInstance();
        for (Field field : entityType.getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.isAnnotationPresent(ManyToOne.class)
                    ? resolveRelation(field, rs)
                    : rs.getObject(getColumnName(field));
            field.set(entity, value);
        }
        return entity;
    }

    private Object resolveRelation(Field relationField, ResultSet rs) throws Exception {
        Class<?> targetEntity = relationField.getType();
        Object relationId = rs.getObject(getColumnName(relationField) + "_id");
        return relationId != null ? find(targetEntity, relationId) : null;
    }

    private String getTableName(Class<?> entityClass) {
        return entityClass.getSimpleName().toLowerCase();
    }

    private Field getIdField(Class<?> entityClass) {
        Field idField = Arrays.stream(entityClass.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow();
        idField.setAccessible(true); // Делаем поле доступным
        return idField;
    }

    private List<Field> getNonIdFields(Class<?> entityClass) {
        List<Field> fields = new ArrayList<>();
        for (Field field : entityClass.getDeclaredFields()) {
            if (!field.isAnnotationPresent(Id.class)) {
                field.setAccessible(true); // Делаем поле доступным
                fields.add(field);
            }
        }
        return fields;
    }

    private String getColumnName(Field field) {
        return field.isAnnotationPresent(ManyToOne.class)
                ? field.getName() + "_id"
                : field.getName();
    }

    private void setParameters(PreparedStatement stmt, Object entity, List<Field> fields) throws Exception {
        int index = 1;
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.isAnnotationPresent(ManyToOne.class)
                    ? getIdFromEntity(field.get(entity))
                    : field.get(entity);
            stmt.setObject(index++, value);
        }
    }

    private Object getIdFromEntity(Object entity) throws Exception {
        if (entity == null) return null;
        Field idField = getIdField(entity.getClass());
        idField.setAccessible(true);
        return idField.get(entity);
    }

    private void setGeneratedId(ResultSet generatedKeys, Object entity, Field idField) throws Exception {
        if (generatedKeys.next()) {
            idField.setAccessible(true);
            idField.set(entity, generatedKeys.getObject(1));
        }
    }
}