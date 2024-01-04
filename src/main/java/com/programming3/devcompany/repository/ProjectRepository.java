package com.programming3.devcompany.repository;

import com.programming3.devcompany.domain.Project;

import java.util.List;

public interface ProjectRepository {

    void addProject(Project project);

    void deleteById(Integer id);

    List<Project> getAllProjects();

    Project getOneById(Integer id);
}
