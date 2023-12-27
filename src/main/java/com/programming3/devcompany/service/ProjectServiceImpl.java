package com.programming3.devcompany.service;

import com.programming3.devcompany.domain.Developer;
import com.programming3.devcompany.domain.Office;
import com.programming3.devcompany.domain.Project;
import com.programming3.devcompany.repository.OfficeRepository;
import com.programming3.devcompany.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public void createProject(Project project) {
        projectRepository.addProject(project);
    }

    @Override
    public void createProject(String projectName, Integer projectBudget, Integer projectId) {
        projectRepository.addProject(new Project(
                projectName,
                projectBudget,
                projectId
        ));
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.getAllProjects();
    }

    @Override
    public Project getProjectById(Integer id) {
        return projectRepository.getAllProjects()
                .stream()
                .filter(project -> project.getProjectId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Project> getProjectsForDeveloper(Developer developer) {
        return projectRepository.getAllProjects()
                .stream()
                .filter(project -> project.getDevelopers().contains(developer))
                .collect(Collectors.toList());
    }
}
