package com.oris.lab12.mapper;

import com.oris.lab12.model.Month;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MonthMapper implements RowMapper<Month> {
    @Override
    public Month mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Month(rs.getLong("id"), rs.getString("name"), rs.getString("season"));
    }
}
