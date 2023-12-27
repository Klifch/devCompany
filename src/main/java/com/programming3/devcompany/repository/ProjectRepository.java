package com.programming3.devcompany.repository;

import com.programming3.devcompany.domain.Project;

import java.util.List;

public interface ProjectRepository {

    void addProject(Project project);

    List<Project> getAllProjects();
}
