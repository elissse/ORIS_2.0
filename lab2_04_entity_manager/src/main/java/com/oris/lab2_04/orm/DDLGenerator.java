package com.oris.lab2_04.orm;

import com.oris.lab2_04.annotation.Entity;
import com.oris.lab2_04.annotation.Id;
import com.oris.lab2_04.annotation.ManyToOne;
import com.oris.lab2_04.helper.GraphSorter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DDLGenerator {
    public static List<String> generateAllTables(List<Class<?>> entityClasses) {
        List<Class<?>> sortedClasses = GraphSorter.topologicalSort(entityClasses);
        List<String> sqlStatements = new ArrayList<>();
        for (Class<?> clazz : sortedClasses) {
            sqlStatements.add(generateCreateTable(clazz));
        }
        return sqlStatements;
    }

    public static String generateCreateTable(Class<?> entityClass) {
        List<String> statements = new ArrayList<>();
        if (!entityClass.isAnnotationPresent(Entity.class)) {
            throw new IllegalArgumentException("Class is not an entity: " + entityClass.getName());
        }
        String tableName = entityClass.getSimpleName().toLowerCase();
        List<Field> fields = Arrays.stream(entityClass.getDeclaredFields())
                .filter(f -> !f.isSynthetic())
                .toList();

        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS ").append(tableName).append(" (\n");

        for (Field field : fields) {
            String columnName = field.getName();
            String sqlType = mapJavaToSqlType(field.getType());
            if (field.isAnnotationPresent(Id.class)) {
                sql.append("    ").append(columnName)
                        .append(" SERIAL PRIMARY KEY,\n");
                continue;
            }
            if (field.isAnnotationPresent(ManyToOne.class)) {
                Class<?> targetEntity = field.getType();
                String fkColumn = columnName + "_id";
                String referencedTable = targetEntity.getSimpleName().toLowerCase();

                sql.append("    ").append(fkColumn)
                        .append(" INTEGER REFERENCES ")
                        .append(referencedTable).append("(id),\n");
                continue;
            }
            sql.append("    ").append(columnName)
                    .append(" ").append(sqlType).append(",\n");
        }

        if (!fields.isEmpty()) {
            sql.delete(sql.length() - 2, sql.length());
        }

        sql.append("\n);");

        return sql.toString();
    }

    private static String mapJavaToSqlType(Class<?> javaType) {
        if (javaType == String.class) {
            return "VARCHAR(255)";
        } else if (javaType == Integer.class || javaType == int.class) {
            return "INTEGER";
        } else if (javaType == Long.class || javaType == long.class) {
            return "BIGINT";
        }
        return "TEXT";
    }
}