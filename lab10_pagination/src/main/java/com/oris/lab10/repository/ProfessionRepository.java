package com.oris.lab10.repository;

import com.oris.lab10.model.Profession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessionRepository {
    private static final String SQL_COUNT = "SELECT COUNT(*) FROM dict_profession";
    private static final String SQL_GET_ALL_BY_NAME = "select * from dict_profession where upper(name) like concat( '%',?,'%') order by id limit 25 offset ?";

    public int countRows() {
        Connection connection = null;
        try {
            connection = DbWork.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_COUNT);
            ResultSet executedQuery = statement.executeQuery();
            executedQuery.next();
            return executedQuery.getInt("count");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Profession> getAllByName(String name, int page) {
        Connection connection = null;
        List<Profession> clients = new ArrayList<>();
        try {
            connection = DbWork.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL_BY_NAME);
            statement.setString(1, name);
            statement.setInt(2, page);
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Profession client = new Profession(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("actualitystatus"));
                clients.add(client);
            }
            return clients;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
