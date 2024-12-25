package com.oris.lab11.mapper;

import com.oris.lab11.model.Animal;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnimalMapper implements RowMapper<Animal> {
    @Override
    public Animal mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Animal(rs.getLong("id"), rs.getString("name"), rs.getString("sound"));
    }
}
