package com.oris.lab_07_filter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/oris_db";
    private static final String USER = "elise";
    private static final String PASSWORD = "superuser";
    private static Connection connection;
    private static DBConnection instance;


    private static void initDB() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            instance = new DBConnection();
            initDB();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized Connection getConnection() {
        if (connection == null) {
            synchronized (DBConnection.class) {
                if (instance == null) {
                    instance = new DBConnection();
                    initDB();
                }
            }
        }
        return connection;
    }

    public static synchronized DBConnection getInstance() {
        if (instance == null) {
            synchronized (DBConnection.class) {
                if (instance == null) {
                    instance = new DBConnection();
                    initDB();
                }
            }
        }
        return instance;
    }

    public static void releaseConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


