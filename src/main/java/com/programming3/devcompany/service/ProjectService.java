package com.programming3.devcompany.service;

import com.programming3.devcompany.domain.Developer;
import com.programming3.devcompany.domain.Project;

import java.sql.SQLException;
import java.util.List;

public interface ProjectService {

    void createProject(Project project);

    void createProject(String projectName, Double projectBudget);

    List<Project> getAllProjects();

    Project getProjectById(Integer id);

    List<Project> getProjectsForDeveloper(Developer developer);

    void deleteById(Integer id);

    default List<Project> findAllByBudgetLower(Double budget) {
        return null;
    }

    default List<Project> findAllByBudgetHigher(Double budget) {
        return null;
    }

    default void triggerSQLException() throws SQLException {};
}
