package com.programming3.devcompany.repository;

import com.programming3.devcompany.domain.Developer;
import com.programming3.devcompany.domain.Office;
import com.programming3.devcompany.domain.Position;
import com.programming3.devcompany.domain.Project;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("h2")
public class JdbcProjectRepositoryImpl implements ProjectRepository {

    private JdbcTemplate jdbcTemplate;

    public JdbcProjectRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void loadDevelopers(Project project) {
        String sql = "SELECT * FROM Developer WHERE id IN (SELECT developer_id FROM Developer_Project WHERE project_id = ?)";
        int projId = project.getProjectId();

        List<Developer> developers = jdbcTemplate.query(
                sql,
                new Object[]{projId},
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

        project.setDevelopers(developers);
    }

    public void loadOffice(Project project) {
        String sql = "SELECT * FROM Office WHERE id IN (SELECT office_id FROM Office_Project WHERE project_id = ?)";
        int projId = project.getProjectId();

        List<Office> office = jdbcTemplate.query(
                sql,
                new Object[]{projId},
                (rs, rowNum) ->
                        new Office(
                                rs.getString("name"),
                                rs.getString("address"),
                                rs.getInt("id")
                        )
        );

        if (!office.isEmpty()) {
            project.setProjectOffice(office.get(0));
        }
    }

    @Override
    public void addProject(Project project) {
        String sql = "INSERT INTO Project (name, budget) VALUES (?, ?)";
        jdbcTemplate.update(sql,
                project.getProjectName(),
                project.getProjectBudget());
    }

    @Override
    public void deleteById(Integer id) {
        jdbcTemplate.update("DELETE FROM Developer_Project WHERE project_id = ?", id);
        jdbcTemplate.update("DELETE FROM Office_Project WHERE project_id = ?", id);
        jdbcTemplate.update("DELETE FROM Project WHERE id = ?", id);
    }

    @Override
    public List<Project> getAllProjects() {
        String sql = "SELECT * FROM Project";

        List<Project> projects = jdbcTemplate.query(
                sql,
                (rs, rowNum) ->
                        new Project(
                                rs.getString("name"),
                                rs.getDouble("budget"),
                                rs.getInt("id")
                )
        );

        projects.forEach(this::loadDevelopers);
        projects.forEach(this::loadOffice);

        return projects;
    }

    @Override
    public Project getOneById(Integer id) {
        String sql = "SELECT * FROM Project WHERE ID = ?";
        Project project = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
                new Project(
                        rs.getString("name"),
                        rs.getDouble("budget"),
                        rs.getInt("id")
                )
        );

        if (project != null) {
            loadDevelopers(project);
            loadOffice(project);
        }

        return project;
    }


}
