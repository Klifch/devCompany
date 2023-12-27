package com.programming3.devcompany.repository;

import com.programming3.devcompany.domain.Project;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProjectRepositoryImpl implements ProjectRepository {

    private List<Project> projects = new ArrayList<>();

    @Override
    public void addProject(Project project) {
        if (!projects.contains(project)) {
            projects.add(project);
        }
    }

    @Override
    public List<Project> getAllProjects() {
        return projects;
    }
}
