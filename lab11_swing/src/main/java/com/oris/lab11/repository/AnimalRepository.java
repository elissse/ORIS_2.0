package com.oris.lab11.repository;

import com.oris.lab11.mapper.AnimalMapper;
import com.oris.lab11.model.Animal;

import java.sql.*;

public class AnimalRepository {

    private final AnimalMapper mapper;
    //language=sql
    private static final String SQL_SAVE = "INSERT INTO animal (id, name, sound) VALUES ((SELECT NEXTVAL('animal_sequence')), ?, ?)";

    public AnimalRepository(AnimalMapper mapper) {
        this.mapper = mapper;
    }

    public void save(Animal entity) {
        Connection connection = null;
        try {
            connection = DbWork.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_SAVE);
            statement.setString(1, entity.name());
            statement.setString(2, entity.sound());
            System.out.println(statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("couldn't save animal to the table");
        }
    }
}