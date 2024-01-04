package com.programming3.devcompany.repository;

import com.programming3.devcompany.domain.Project;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("h2")
public class JdbcProjectRepository implements ProjectRepository {

    private JdbcTemplate jdbcTemplate;

    public JdbcProjectRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addProject(Project project) {
        String sql = "INSERT INTO Project (name, budget) VALUES (?, ?)";
        jdbcTemplate.update(sql,
                project.getProjectName(),
                project.getProjectBudget());
    }

    @Override
    public List<Project> getAllProjects() {
        String sql = "SELECT * FROM Project";
        return jdbcTemplate.query(
                sql,
                (rs, rowNum) ->
                        new Project(
                                rs.getString("name"),
                                rs.getDouble("budget"),
                                rs.getInt("id")
                ));
    }

    @Override
    public Project getOneById(Integer id) {
        String sql = "SELECT * FROM Project WHERE ID = ?";
        return (Project) jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
                new Project(
                        rs.getString("name"),
                        rs.getDouble("budget"),
                        rs.getInt("id")
                ));
    }
}
