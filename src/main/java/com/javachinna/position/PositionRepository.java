package com.javachinna.position;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PositionRepository {

    private final JdbcTemplate jdbcTemplate;
    private RowMapper<Position> RowMapper = (ResultSet resultSet, int n) -> new Position(resultSet.getLong("position_id"), resultSet.getString("name"));

    public void save(Position position) {
        var r = jdbcTemplate.update("insert into positions (name) values (?)", position.name);
    }

    public void update(Position position) {
        jdbcTemplate.update("update positions set name = ? where position_id = ?", position.name, position.positionId);
    }

    public Position findById(Long positionId) {
        Position position = jdbcTemplate.queryForObject("select * from positions where position_id = ?", RowMapper, positionId);
        return position;
    }

    public List<Position> findAll() {
        return jdbcTemplate.query("select * from positions order by position_id", RowMapper);
    }

    public void deletePosition(Long positionId) {
        jdbcTemplate.update("delete from positions where position_id = ?", positionId);
    }


}
