package com.programming3.devcompany.repository;

import com.programming3.devcompany.domain.Developer;
import com.programming3.devcompany.domain.Position;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Locale;

@Repository
@Profile("h2")
public class JdbcDeveloperRepositoryImpl implements DeveloperRepository {

    private JdbcTemplate jdbcTemplate;

    public JdbcDeveloperRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addDeveloper(Developer developer) {
        String sql = "INSERT INTO Developer (first_name, last_name, age, salary, hire_date, position) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                developer.getFirstName(),
                developer.getLastName(),
                developer.getAge(),
                developer.getSalary(),
                developer.getEndOfContract(),
                developer.getPosition().toString().toUpperCase());
    }

    @Override
    public List<Developer> getAllDevelopers() {
        String sql = "SELECT * FROM Developer";
        return jdbcTemplate.query(
                sql,
                (rs, rowNum) ->
                        new Developer(
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getInt("age"),
                                rs.getDouble("salary"),
                                rs.getDate("hire_date").toLocalDate(),
                                Position.valueOf(rs.getString("position")),
                                rs.getInt("id")
                        )
        );
    }

    @Override
    public Developer findById(int developerId) {
        String sql = "SELECT * FROM Developer WHERE ID = ?";
        return (Developer) jdbcTemplate.queryForObject(sql, new Object[]{developerId}, (rs, rowNum) ->
                new Developer(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getInt("age"),
                        rs.getDouble("salary"),
                        rs.getDate("hire_date").toLocalDate(),
                        Position.valueOf(rs.getString("position")),
                        rs.getInt("id")
                ));
    }




}
