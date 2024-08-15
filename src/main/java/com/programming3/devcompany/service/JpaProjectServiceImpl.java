package com.programming3.devcompany.service;

import com.programming3.devcompany.domain.Developer;
import com.programming3.devcompany.domain.Project;
import com.programming3.devcompany.repository.JpaRepProjectRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@Profile("jparep")
@Primary
public class JpaProjectServiceImpl implements ProjectService {

    private JpaRepProjectRepository jpaRepProjectRepository;

    public JpaProjectServiceImpl(JpaRepProjectRepository jpaRepProjectRepository) {
        this.jpaRepProjectRepository = jpaRepProjectRepository;
    }

    @Override
    public void createProject(Project project) {
        jpaRepProjectRepository.save(project);
    }

    @Override
    public void createProject(String projectName, Double projectBudget) {

    }

    @Override
    public List<Project> getAllProjects() {
        return jpaRepProjectRepository.findAll();
    }

    @Override
    public Project getProjectById(Integer id) {
        return jpaRepProjectRepository.findById(id).orElse(null);
    }

    @Override
    public List<Project> getProjectsForDeveloper(Developer developer) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        jpaRepProjectRepository.deleteById(id);
    }

    @Override
    public List<Project> findAllByBudgetLower(Double budget) {
        return jpaRepProjectRepository.findAllByProjectBudgetBefore(budget);
    }

    @Override
    public List<Project> findAllByBudgetHigher(Double budget) {
        return jpaRepProjectRepository.findAllByProjectBudgetAfter(budget);
    }

    @Override
    public void triggerSQLException() throws SQLException {
        throw new SQLException("YO, stop it please!");
    }
}
