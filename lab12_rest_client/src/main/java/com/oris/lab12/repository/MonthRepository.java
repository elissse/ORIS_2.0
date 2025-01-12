package com.oris.lab12.repository;

import com.oris.lab12.mapper.MonthMapper;
import com.oris.lab12.model.Month;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MonthRepository {

    private final MonthMapper mapper;
    //language=sql
    private static final String SQL_SAVE = "INSERT INTO months (name, season) VALUES (?, ?)";
    private static final String SQL_GET_ALL = "SELECT * FROM months";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM months WHERE id = ?";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM months WHERE id = ?";

    public MonthRepository(MonthMapper mapper) {
        this.mapper = mapper;
    }

    public List<Month> getAll() {
        Connection connection = null;
        try {
            connection = DbWork.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL);
            ResultSet executedQuery = statement.executeQuery();
            List<Month> userList = new ArrayList<>();
            while (executedQuery.next()) {
                userList.add(mapper.mapRow(executedQuery, 0));
            }
            return userList;
        } catch (SQLException e) {
            throw new RuntimeException("couldn't get all elements sorry :(");
        }
    }

    public Month save(Month entity) {
        Connection connection = null;
        try {
            connection = DbWork.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_SAVE);
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSeason());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("couldn't save month to the table");
        }
        return entity;
    }

    public Month findById(final Long id) {
        Connection connection = null;
        try {
            connection = DbWork.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setLong(1, id);
            ResultSet executedQuery = statement.executeQuery();
            executedQuery.next();
            return mapper.mapRow(executedQuery, 0);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public boolean deleteById(final Long id) {
        Connection connection = null;
        try {
            findById(id);
            connection = DbWork.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID);
            try {
                statement.setLong(1, id);
                statement.executeQuery();
            } catch (SQLException e) {
                throw new RuntimeException();
            }

            return true;
        } catch (RuntimeException | SQLException e) {
            return false;
        }
    }

}