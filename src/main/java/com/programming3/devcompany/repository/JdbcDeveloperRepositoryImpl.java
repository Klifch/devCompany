package com.programming3.devcompany.repository;

import com.programming3.devcompany.domain.Developer;
import com.programming3.devcompany.domain.Position;
import com.programming3.devcompany.domain.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Repository
@Profile("h2")
public class JdbcDeveloperRepositoryImpl implements DeveloperRepository {

    Logger logger = LoggerFactory.getLogger(JdbcDeveloperRepositoryImpl.class);
    private JdbcTemplate jdbcTemplate;

    public JdbcDeveloperRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void loadProjects(Developer developer) {
        String sql = "SELECT * FROM Project WHERE id IN (SELECT project_id FROM Developer_Project WHERE developer_id = ?)";
        int devId = developer.getId();

        List<Project> projects = jdbcTemplate.query(sql,
                new Object[]{devId},
                (rs, rowNum) ->
                        new Project(
                                rs.getString("name"),
                                rs.getDouble("budget"),
                                rs.getInt("id")
                        )
        );

        logger.info("All projects {} for developer {}", projects, developer);
          developer.setProjects(projects);
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
    @Transactional
    public void deleteById(Integer id) {
        jdbcTemplate.update("DELETE FROM Developer_Project WHERE developer_id = ?", id);
        jdbcTemplate.update("DELETE FROM Developer WHERE id = ?", id);
    }

    @Override
    public List<Developer> getAllDevelopers() {
        logger.info("call to retrieve all developers form H2DB");
        String sql = "SELECT * FROM Developer";
        List<Developer> developers = jdbcTemplate.query(
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


        logger.info("Retrieved List of Developers! {}", developers);
        developers.forEach(this::loadProjects);
        logger.info("Developers received their projects! {}", developers);

        return developers;
    }

    @Override
    public Developer findById(int developerId) {
        String sql = "SELECT * FROM Developer WHERE ID = ?";
        Developer developer = jdbcTemplate.queryForObject(sql, new Object[]{developerId}, (rs, rowNum) ->
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

        if (developer != null) {
            loadProjects(developer);
        }

        return developer;
    }




}
