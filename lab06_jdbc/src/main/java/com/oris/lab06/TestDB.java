package com.oris.lab06;

import java.sql.*;

public class TestDB {
    final static String url = "jdbc:postgresql://localhost:5432/agona_db";
    final static String username = "elise";
    final static String password = "superuser";
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
           // Boolean result = statement.execute("create table users(id bigint primary key, name varchar(50))");

            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where id = ?");
            preparedStatement.setLong(1, 1);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                System.out.println(resultSet.getLong("id"));
                System.out.println(resultSet.getString("name"));
            }
            resultSet.close();

            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
