package com.programming3.devcompany.service;

import com.programming3.devcompany.domain.Developer;
import com.programming3.devcompany.domain.Project;

import java.util.List;

public interface ProjectService {

    void createProject(Project project);

    void createProject(String projectName, Integer projectBudget);

    List<Project> getAllProjects();

    Project getProjectById(Integer id);

    List<Project> getProjectsForDeveloper(Developer developer);
}
